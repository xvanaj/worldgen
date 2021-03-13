package renderer;

import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.scenes.scene2d.Stage;
import renderer.api.OrthoMapRenderer;
import squidpony.squidmath.Coord;
import util.LoggingUtils;
import worldgen.output.WorldMap;

import java.util.*;

public class LPCStyleRenderer extends OrthoMapRenderer {

    private List<SpriteCache> spriteCaches;

    private Set<Integer> waterRegionCodes;
    private Map<Integer, List<Coord>> tilesByBiome;
    private SpriteCache currentCache;
    private int currentCacheSize;

    public LPCStyleRenderer(WorldMap worldMap, Stage stage) {
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
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 11, 18, 20);
                    }
                    break;
                    case 1: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 11, 18, 20);
                    }
                    break;
                       case 2: {
                           addBitmaskedTexturesOnPosition(coord, biomeName, 14, 16, 18, 20);
                       }
                    break;
                    case 3: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 16, 18, 20);
                    }
                    break;
                    case 4: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 8, 18, 20);
                    }
                    break;
                    case 5: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 8, 18, 20);
                    }
                    break;
                    case 6: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 1, 18, 20);
                    }
                    break;
                    case 7: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 1, 18, 20);
                    }
                    break;
                    case 8: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 11, 19, 20);
                    }
                    break;
                    case 9: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 11, 19, 20);
                    }
                    break;
                    case 10: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 16, 19, 20);
                    }
                    break;
                    case 11: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 16, 19, 20);
                    }
                    break;
                    case 12: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 8, 19, 20);
                    }
                    break;
                    case 13: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 8, 19, 20);
                    }
                    break;
                    case 14: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 1, 19, 20);
                    }
                    break;
                    case 15: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 1, 19, 20);
                    }
                    break;
                    case 16: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 10, 18, 19);
                    }
                    break;
                    case 17: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 10, 18, 19);
                    }
                    break;
                    case 18: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 5, 18, 19);
                    }
                    break;
                    case 19: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 5, 18, 19);
                    }
                    break;
                    case 20: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 6, 18, 19);
                    }
                    break;
                    case 21: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 6, 18, 19);
                    }
                    break;
                    case 22: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 15, 18, 19);
                    }
                    break;
                    case 23: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 15, 18, 19);
                    }
                    break;
                    case 24: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 10, 19, 19);
                    }
                    break;
                    case 25: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 10, 19, 19);
                    }
                    break;
                    case 26: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 5, 19, 19);
                    }
                    break;
                    case 27: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 5, 19, 19);
                    }
                    break;
                    case 28: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 6, 19, 19);
                    }
                    break;
                    case 29: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 6, 19, 19);
                    }
                    break;
                    case 30: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 15, 19, 19);
                    }
                    break;
                    case 31: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 15, 19, 19);
                    }
                    break;
                    case 32: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 11, 8, 20);
                    }
                    case 33: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 11, 8, 20);
                    }
                    case 34: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 16, 8, 20);
                    }
                    case 35: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 16, 8, 20);
                    }
                    case 36: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 8, 18, 20);
                    }
                    case 37: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 8, 8, 20);
                    }
                    break;
                    case 38: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 1, 8, 20);
                    }
                    break;
                    case 39: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 1, 8, 20);
                    }
                    break;
                    case 40: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 11, 1, 20);
                    }
                    break;
                    case 41: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 11, 1, 20);
                    }
                    break;
                    case 42: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 16, 1, 20);
                    }
                    break;
                    case 43: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 16, 1, 20);
                    }
                    break;
                    case 44: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 8, 1, 20);
                    }
                    break;
                    case 45: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 6, 1, 20);
                    }
                    break;
                    case 46: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 1, 1, 20);
                    }
                    break;
                    case 47: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 1, 1, 20);
                    }
                    break;
                    case 48: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 10, 8, 19);
                    }
                    break;
                    case 49: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 10, 8, 19);
                    }
                    break;
                    case 50: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 5, 8, 19);
                    }
                    break;
                    case 51: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 16, 8, 19);
                    }
                    break;
                    case 52: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 8, 8, 19);
                    }
                    break;
                    case 53: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 6, 8, 19);
                    }
                    break;
                    case 54: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 15, 8, 19);
                    }
                    break;
                    case 55: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 15, 8, 19);
                    }
                    break;

                    case 56: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 10, 1, 19);
                    }
                    break;
                    case 57: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 10, 1, 19);
                    }
                    break;
                    case 58: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 5, 1, 19);
                    }
                    break;
                    case 59: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 5, 8, 19);
                    }
                    break;
                    case 60: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 6, 8, 19);
                    }
                    break;
                    case 61: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 6, 1, 19);
                    }
                    break;
                    case 62: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 15, 1, 19);
                    }
                    break;
                    case 63: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 15, 1, 19);
                    }
                    break;
                    case 64: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 11, 14, 16);
                        break;
                    }
                    case 65: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 11, 14, 16);
                        break;
                    }
                    case 66: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 16, 14, 16);
                        break;
                    }
                    case 67: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 16, 14, 16);
                        break;
                    }
                    case 68: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 1, 14, 16);
                        break;
                    }
                    case 69: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 8, 14, 16);
                        break;
                    }
                    case 70: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 1, 14, 16);
                        break;
                    }
                    case 71: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 1, 14, 16);
                        break;
                    }
                    case 72: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 11, 2, 16);
                        break;
                    }
                    case 73: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 11, 2, 16);
                        break;
                    }
                    case 74: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 16, 2, 16);
                        break;
                    }
                    case 75: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 16, 2, 16);
                        break;
                    }
                    case 76: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 8, 2, 16);
                        break;
                    }
                    case 77: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 8, 2, 16);
                        break;
                    }
                    case 78: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 1, 2, 16);
                        break;
                    }
                    case 79: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 1, 2, 16);
                        break;
                    }
                    case 80: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 10, 14, 1);
                        break;
                    }

                    case 81: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 10, 14, 1);
                        break;
                    }
                    case 82: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 5, 14, 1);
                        break;
                    }
                    case 83: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 5, 14, 1);
                        break;
                    }
                    case 84: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 6, 14, 1);
                        break;
                    }
                    case 85: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 11, 14, 1);
                        break;
                    }
                    case 86: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 15, 14, 1);
                        break;
                    }
                    case 87: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 1, 14, 1);
                        break;
                    }
                    case 88: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 10, 2, 1);
                        break;
                    }
                    case 89: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 10, 2, 1);
                        break;
                    }
                    case 90: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 5, 2, 1);
                        break;
                    }
                    case 91: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 5, 2, 1);
                        break;
                    }
                    case 92: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 6, 2, 1);
                        break;
                    }
                    case 93: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 6, 2, 1);
                        break;
                    }
                    case 94: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 15, 2, 1);
                        break;
                    }
                    case 95: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 15, 2, 1);
                        break;
                    }
                    case 96: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 11, 6, 16);
                        break;
                    }
                    case 97: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 11, 6, 16);
                        break;
                    }
                    case 98: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 16, 6, 16);
                        break;
                    }
                    case 99: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 16, 6, 16);
                        break;
                    }
                    case 100: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 8, 6, 16);
                        break;
                    }
                    
                    case 101: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 8, 6, 16);
                        break;
                    }
                    case 102: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 1, 6, 16);
                        break;
                    }
                    case 103: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 1, 6, 16);
                        break;
                    }
                    case 104: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 11, 15, 16);
                        break;
                    }
                    case 105: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 11, 15, 16);
                        break;
                    }
                    case 106: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 16, 15, 16);
                        break;
                    }
                    case 107: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 16, 15, 16);
                        break;
                    }
                    case 108: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 8, 15, 16);
                        break;
                    }
                    case 109: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 8, 15, 16);
                        break;
                    }
                    case 110: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 1, 15, 16);
                        break;
                    }
                    case 111: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 1, 15, 16);
                        break;
                    }
                    case 112: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 10, 6, 1);
                        break;
                    }
                    case 113: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 10, 6, 1);
                        break;
                    }
                    case 114: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 5, 6, 1);
                        break;
                    }
                    case 115: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 5, 6, 1);
                        break;
                    }
                    case 116: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 8, 6, 1);
                        break;
                    }
                    case 117: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 8, 6, 1);
                        break;
                    }
                    case 118: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 15, 6, 1);
                        break;
                    }
                    case 119: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 15, 6, 1);
                        break;
                    }
                    case 120: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 10, 15, 1);
                        break;
                    }

                    case 121: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 10, 15, 1);
                        break;
                    }
                    case 122: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 16, 15, 1);
                        break;
                    }
                    case 123: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 5, 15, 1);
                        break;
                    }
                    case 124: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 6, 15, 1);
                        break;
                    }
                    case 125: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 6, 15, 1);
                        break;
                    }
                    case 126: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 15, 15, 1);
                        break;
                    }
                    case 127: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 15, 15, 1);
                        break;
                    }
                    case 128: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 11, 18, 13);
                        break;
                    }
                    case 129: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 10, 18, 13);
                        break;
                    }
                    case 130: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 16, 18, 13);
                        break;
                    }

                    case 131: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 16, 18, 13);
                        break;
                    }
                    case 132: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 8, 18, 13);
                        break;
                    }
                    case 133: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 13, 8, 18, 13);
                        break;
                    }
                    case 134: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 14, 1, 18, 13);
                        break;
                    }
                    case 135: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 2, 1, 18, 13);
                        break;
                    }
                    case 136: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 11, 19, 13);
                        break;
                    }
                    case 137: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 11, 19, 13);
                        break;
                    }
                    case 138: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 6, 16, 19, 13);
                        break;
                    }
                    case 139: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 16, 19, 13);
                        break;
                    }
                    case 140: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 13, 19, 13);
                        break;
                    }

                    case 141: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 13, 19, 13);
                        break;
                    }
                    case 142: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 13, 19, 13);
                        break;
                    }
                    case 143: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 13, 19, 13);
                        break;
                    }
                    case 144: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 13, 19, 13);
                        break;
                    }
                    case 145: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 13, 19, 13);
                        break;
                    }case 146: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 13, 19, 13);
                        break;
                    }
                    case 147: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 13, 19, 13);
                        break;
                    }
                    case 148: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 13, 19, 13);
                        break;
                    }case 149: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 13, 19, 13);
                        break;
                    }
                    case 150: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 9, 13, 19, 13);
                        break;
                    }




                    



                    case 252: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 10, 6, 15, 15);
                    }
                    case 253: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 5, 6, 15, 15);
                    }
                    case 254: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 1, 15, 15, 15);
                    }
                    case 255: {
                        addBitmaskedTexturesOnPosition(coord, biomeName, 15, 15, 15, 15);
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

    private void addBitmaskedTexturesOnPosition(Coord coord, String biomeName, int lt, int rt, int lb, int rb) {
        addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y + 1), biomeName, lt);  //lt
        addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y + 1), biomeName, rt);  //rt
        addBitmaskedTextureOnPosition(Coord.get(coord.x, coord.y), biomeName, lb);  //lb
        addBitmaskedTextureOnPosition(Coord.get(coord.x + 1, coord.y), biomeName, rb);  //rb
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
