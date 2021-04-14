package com.vana.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vana.renderer.api.OrthoMapRenderer;
import com.vana.util.LoggingUtils;
import com.vana.worldgen.output.WorldMap;
import squidpony.squidmath.Coord;

import java.util.*;

public class SpriteCacheOrthoMapRenderer extends OrthoMapRenderer {

    private List<SpriteCache> spriteCaches;
    private boolean worldWrap = true;

    private Map<Integer, List<Coord>> tilesByBiome;
    private SpriteCache currentCache;
    private int currentCacheSize;

    public SpriteCacheOrthoMapRenderer(WorldMap worldMap, Stage stage) {
        super(worldMap, stage);
        init();
        createCachedMap();
    }

    private void init() {
        tilesByBiome = new HashMap<>();

        //sort tiles by biomes. Lists should be probably sorted by some priority but thats not implemented yet
        for (int i = width - 1; i >= 0; i--) {
            for (int j = height - 1; j >= 0; j--) {
                int regionId = worldMap.getBiomesData()[i][j];

                if (tilesByBiome.get(regionId) == null) {
                    tilesByBiome.put(regionId, new ArrayList<>());
                }

                tilesByBiome.get(regionId).add(Coord.get(i, j));
            }
        }
    }

    @Override
    protected void drawTerrain() {
        if (drawTerrain) {
            for (SpriteCache cache : spriteCaches) {
                cache.setProjectionMatrix(camera.combined);
                cache.begin();
                cache.draw(0);
                cache.end();
            }
        }
    }

    public void createCachedMap() {
        LoggingUtils.startCounting();

        if (spriteCaches == null) {
            spriteCaches = new ArrayList<>();
        }

        spriteCaches.stream().forEach(spriteCache -> spriteCache.dispose());
        spriteCaches.clear();
        currentCache = new SpriteCache(8000, true);

        currentCache.beginCache();
        currentCacheSize = 0;

        /*for (int i = width - 1; i >= 0; i--) {
            for (int j = height - 1; j >= 0; j--) {
                if (cacheSize >= 7999) {
                    cacheSize = 0;
                    spriteCaches.add(currentCache);
                    currentCache.endCache();
                    currentCache = new SpriteCache(8000, false);
                    currentCache.beginCache();
                }

                String biomeName = worldMap.getBiomeNameTable()[worldMap.getBiomesData()[i][j]];
                int biomeBitmask = bitmaskData[i][j] == 2 ? 2 : 15;

                currentCache.add(BiomeTextureMapper.squidBiomeTextureMapper.getBiomeTexture(biomeName, biomeBitmask),
                //currentCache.add(BiomeTextureMapper.squidBiomeTextureMapper2.getBiomeTexture(biomeName),
                        i * tileWidth,
                        j * tileHeight);
                cacheSize++;

            }
        }*/

        Set<Integer> processed = new HashSet<>();

        for (Integer regionCode : tilesByBiome.keySet()) {
            for (Coord coord : tilesByBiome.get(regionCode)) {
                if (currentCacheSize >= 7999) {
                    currentCacheSize = 0;
                    spriteCaches.add(currentCache);
                    currentCache.endCache();
                    currentCache = new SpriteCache(8000, false);
                    currentCache.beginCache();
                }

                String biomeName = worldMap.getBiomeNameTable()[worldMap.getBiomesData()[coord.x][coord.y]];

                int bitmask = worldMap.getBitmaskData()[coord.x][coord.y];
                if (bitmask == 0) {
                    //addBitmaskedTextureOnPosition(coord, biomeName, 0);
                    addBitmaskedTextureOnPosition(coord, biomeName, 15);    //mid

                    addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y), biomeName, 14);  //l
                    addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y), biomeName, 16);  //r
                    addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y - 1), biomeName, 19);  //b
                    addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y + 1), biomeName, 10);  //t

                    //corners
                    addBitmaskedTextureOnPosition(Coord.get(coord.x-1, coord.y - 1), biomeName, 18);  //lb
                    addBitmaskedTextureOnPosition(Coord.get(coord.x+1, coord.y - 1), biomeName, 20);  //rb
                    addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y + 1), biomeName, 11);  //rt
                    addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y + 1), biomeName, 9);  //lt
                } else {
                    addBitmaskedTextureOnPosition(coord, biomeName, 15);

                    switch (bitmask) {
                        case 1: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y), biomeName, 14);  //l
                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y), biomeName, 16);  //r
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y - 1), biomeName, 19);  //b

                            //corners
                            addBitmaskedTextureOnPosition(Coord.get(coord.x-1, coord.y - 1), biomeName, 18);  //lb
                            addBitmaskedTextureOnPosition(Coord.get(coord.x+1, coord.y - 1), biomeName, 20);
                        }
                        break;
                        case 2: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y), biomeName, 16);
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y - 1), biomeName, 19);
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y + 1), biomeName, 10);  //t

                            //corners
                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y + 1), biomeName, 11);  //rt
                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y - 1), biomeName, 20);  //rb
                        }
                        break;
                        case 3: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y), biomeName, 16);
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y - 1), biomeName, 19);

                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y - 1), biomeName, 20);  //rb
                        }
                        break;
                        case 4: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y), biomeName, 14);  //l
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y + 1), biomeName, 10);  //t
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y - 1), biomeName, 19);  //b

                            addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y + 1), biomeName, 9);  //lt
                            addBitmaskedTextureOnPosition(Coord.get(coord.x-1, coord.y - 1), biomeName, 18);  //lb
                        }
                        break;
                        case 5: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y), biomeName, 14);  //l
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y - 1), biomeName, 19);  //b

                            addBitmaskedTextureOnPosition(Coord.get(coord.x-1, coord.y - 1), biomeName, 18);  //lb
                        }
                        break;
                        case 6: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y + 1), biomeName, 10);  //t
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y - 1), biomeName, 19);  //b
                        }
                        break;
                        case 7: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y - 1), biomeName, 19);  //b
                        }
                        break;
                        case 8: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y), biomeName, 14);  //l
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y + 1), biomeName, 10);  //t
                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y), biomeName, 16);  //r

                            addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y + 1), biomeName, 9);  //lt
                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y + 1), biomeName, 11);  //rt
                        }
                        break;
                        case 9: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y), biomeName, 14);  //l
                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y), biomeName, 16);  //r
                        }
                        break;
                        case 10: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y + 1), biomeName, 10);  //t
                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y), biomeName, 16);  //r

                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y + 1), biomeName, 11);  //rt
                        }
                        break;
                        case 11: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y), biomeName, 16);  //r
                        }
                        break;
                        case 12: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y + 1), biomeName, 10);  //t
                            addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y), biomeName, 14);  //l

                            addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y + 1), biomeName, 9);  //lt
                        }
                        break;
                        case 13: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x - 1, coord.y), biomeName, 14);  //l
                        }
                        break;
                        case 14: {
                            addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y + 1), biomeName, 10);  //t
                        }
                        break;
                        case 15: {

                        }
                        break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + bitmask);
                    }
                }


                    /*                currentCache.add(BiomeTextureMapper.squidBiomeTextureMapper.getBiomeTexture(biomeName, 15),
                        coord.x * tileWidth,
                        coord.y * tileHeight);
                currentCacheSize++;

                int x0 = (coord.x - 1) < 0 ? (width - 1) : (coord.x - 1);
                int x1 = coord.x + 1 >= width ? 0 : coord.x + 1;
                int y0 = coord.y - 1 < 0 ? height - 1 : coord.y - 1;
                int y1 = coord.y + 1 >= height ? 0 : coord.y + 1;

                int biomeCodeNorth = worldMap.getBiomesData()[coord.x][y1];
                int biomeCodeWest = worldMap.getBiomesData()[x0][coord.y];
                int biomeCodeEast = worldMap.getBiomesData()[x1][coord.y];
                int biomeCodeSouth = worldMap.getBiomesData()[coord.x][y0];
                int biomeCodeSW = worldMap.getBiomesData()[x0][y0];
                int biomeCodeSE = worldMap.getBiomesData()[x1][y0];
                int biomeCodeNW = worldMap.getBiomesData()[x0][y1];
                int biomeCodeNE = worldMap.getBiomesData()[x1][y1];

                String biomeNameWest = worldMap.getBiomeNameTable()[biomeCodeWest];
                String biomeNameEast = worldMap.getBiomeNameTable()[biomeCodeEast];
                String biomeNameNorth = worldMap.getBiomeNameTable()[biomeCodeNorth];
                String biomeNameSouth = worldMap.getBiomeNameTable()[biomeCodeSouth];
                String biomeNameSW = worldMap.getBiomeNameTable()[biomeCodeSW];
                String biomeNameSE = worldMap.getBiomeNameTable()[biomeCodeSE];
                String biomeNameNW = worldMap.getBiomeNameTable()[biomeCodeNW];
                String biomeNameNE = worldMap.getBiomeNameTable()[biomeCodeNE];

                boolean n = biomeNameNorth.equals(biomeName) || processed.contains(biomeCodeNorth);
                boolean s = biomeNameSouth.equals(biomeName) || processed.contains(biomeCodeSouth);
                boolean w = biomeNameWest.equals(biomeName) || processed.contains(biomeCodeWest);
                boolean e = biomeNameEast.equals(biomeName) || processed.contains(biomeCodeEast);
                boolean nw = biomeNameNW.equals(biomeName) || processed.contains(biomeCodeNW);
                boolean ne = biomeNameNE.equals(biomeName) || processed.contains(biomeCodeNE);
                boolean sw = biomeNameSW.equals(biomeName) || processed.contains(biomeCodeSW);
                boolean se = biomeNameSE.equals(biomeName) || processed.contains(biomeCodeSE);

                if (!n) {
                    addBorderTexture(coord, biomeNameNorth, 19);
                }

                if (!nw) {
                    addBorderTexture(coord, biomeNameNW, 20);
                }

                if (!ne) {
                    addBorderTexture(coord, biomeNameNE, 18);
                }

                if (!s) {
                    addBorderTexture(coord, biomeNameSouth, 10);
                }

                if (!se) {
                    addBorderTexture(coord, biomeNameSE, 9);
                }

                if (!sw) {
                    addBorderTexture(coord, biomeNameSW, 11);
                }

                if (!e) {
                    if (!se) {
                        addBorderTexture(coord, biomeNameEast, 5);
                    } else {
                        addBorderTexture(coord, biomeNameEast, 14);
                    }
                }

                if (!w) {
                    addBorderTexture(coord, biomeNameWest, 16);
                }*/
            }

            processed.add(regionCode);
        }

        spriteCaches.add(currentCache);
        currentCache.endCache();

        LoggingUtils.logWithTime("Sprite caches recreated");
    }

    private void addBitmaskedTextureOnPosition(Coord coord, String biomeNameNorth, int bitmask) {
        currentCache.add(BiomeTextureMapper.squidBiomeTextureMapper.getBiomeTexture(biomeNameNorth, bitmask),
                coord.x * tileWidth,
                coord.y * tileHeight);

        //max cache size is 8000 so we have to create new cache
        currentCacheSize++;
        if (currentCacheSize >= 7999) {
            currentCacheSize = 0;
            spriteCaches.add(currentCache);
            currentCache.endCache();
            currentCache = new SpriteCache(8000, false);
            currentCache.beginCache();
        }
    }

}
