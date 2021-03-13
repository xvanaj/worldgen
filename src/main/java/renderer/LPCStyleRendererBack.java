package renderer;

import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.scenes.scene2d.Stage;
import renderer.api.OrthoMapRenderer;
import squidpony.squidmath.Coord;
import util.LoggingUtils;
import worldgen.output.WorldMap;

import java.util.*;

public class LPCStyleRendererBack extends OrthoMapRenderer {

    private List<SpriteCache> spriteCaches;

    private Set<Integer> waterRegionCodes;
    private Map<Integer, List<Coord>> tilesByBiome;
    private SpriteCache currentCache;
    private int currentCacheSize;

    public LPCStyleRendererBack(WorldMap worldMap, Stage stage) {
        super(worldMap, stage);
        init();
        createCachedMap();
    }

    private void init() {
        tilesByBiome = new HashMap<>();
        waterRegionCodes = new HashSet<>();

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

                switch (bitmask) {
                    case 0: {
                        addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y), biomeName, 11);
                    }
                    break;
                    case 1: {
                        addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y), biomeName, 11);
                    }
                    break;
                    case 2: {
                        addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y), biomeName, 16);
                    }
                    break;
                    case 3: {
                        addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y), biomeName, 16);
                    }
                    break;
                    case 4: {
                        addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y), biomeName, 8);
                    }
                    break;
                    case 5: {
                        addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y), biomeName, 8);
                    }
                    case 6: {
                        addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y), biomeName, 8);
                    }

                    break;
                    case 255: {
                        addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y), biomeName, 15);
                    }
                    break;

                    /*default:
                        throw new IllegalStateException("Unexpected value: " + bitmask);*/
                }
                

            }

            processed.add(regionCode);
        }

        spriteCaches.add(currentCache);
        currentCache.endCache();

        LoggingUtils.logWithTime("Sprite caches recreated");
    }

    private void addBitmaskedTextureOnPosition(Coord coord, String biomeNameNorth, int i) {
        currentCache.add(BiomeTextureMapper.squidBiomeTextureMapper.getBiomeTexture(biomeNameNorth, i),
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
