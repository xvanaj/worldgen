package renderer;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import util.Utility;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class BiomeTextureMapper {

    private final Map<String, String> biomeTextureMapping;
    private final Map<String, TextureAtlas.AtlasRegion> cache;
    private final TextureAtlas textureAtlas;

    public BiomeTextureMapper(Map<String, String> biomeTextureMapping, TextureAtlas textureAtlas) {
        this.biomeTextureMapping = biomeTextureMapping;
        this.textureAtlas = textureAtlas;
        this.cache = new HashMap<>();
    }

    public TextureAtlas.AtlasRegion getBiomeTexture(final String biomeCode, final int biomeBitmask) {
        final String textureName = biomeTextureMapping.get(biomeCode);

        if (cache.get(biomeCode + biomeBitmask) == null) {

            if (textureAtlas.findRegion(textureName + biomeBitmask) == null) {
                System.out.println("Texture mapping for biomeCode " + biomeCode + " and bitmask " + biomeBitmask + " not found");
                cache.put(biomeCode + biomeBitmask, textureAtlas.findRegion(biomeTextureMapping.get("default") + biomeBitmask));
            } else {
                cache.put(biomeCode + biomeBitmask, textureAtlas.findRegion(textureName + biomeBitmask));
            }

        }

        return cache.get(biomeCode + biomeBitmask);
    }

    public TextureAtlas.AtlasRegion getBiomeTexture(final String biomeCode) {
        final String textureName = biomeTextureMapping.get(biomeCode);

        if (cache.get(biomeCode) == null) {

            if (textureAtlas.findRegion(textureName) == null) {
                System.out.println("Texture mapping for biomeCode " + biomeCode + " not found");
                cache.put(biomeCode, textureAtlas.findRegion(biomeTextureMapping.get("default")));
            } else {
                cache.put(biomeCode, textureAtlas.findRegion(textureName));
            }

        }

        return cache.get(biomeCode);
    }

    public static BiomeTextureMapper squidBiomeTextureMapper = new BiomeTextureMapper(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Ocean", "water/water-"),
            new AbstractMap.SimpleEntry<>("Beach", "dirt/dirt-"),
            new AbstractMap.SimpleEntry<>("Ice", "snow/snow-"),
            new AbstractMap.SimpleEntry<>("Grassland", "lava/lava-"),
            new AbstractMap.SimpleEntry<>("Tundra", "dirt_night/dirt_night-"),
            new AbstractMap.SimpleEntry<>("Woodland", "tundra_grass/tundra_grass-"),
            new AbstractMap.SimpleEntry<>("Savannah", "dirt2/dirt2-"),
            new AbstractMap.SimpleEntry<>("Desert", "sand/sand-"),
            new AbstractMap.SimpleEntry<>("SeasonalForest", "grass/grass-"),
            new AbstractMap.SimpleEntry<>("TemperateRainforest", "tallgrass/tallgrass-"),
            //new AbstractMap.SimpleEntry<>("BorealForest", "swamp/swamp-"),
            new AbstractMap.SimpleEntry<>("BorealForest", "grass/grass-"),
            new AbstractMap.SimpleEntry<>("Rocky", "lavarock/lavarock-"),
            //new AbstractMap.SimpleEntry<>("Savanna", "savanna/savanna-"),
            new AbstractMap.SimpleEntry<>("Savanna", "grass/grass-"),
            new AbstractMap.SimpleEntry<>("TropicalRainforest", "grass/grass-"),
            new AbstractMap.SimpleEntry<>("default", "redsand/redsand-")
    ), Utility.SIMPLE_TEXTURE_ATLAS);

    public static BiomeTextureMapper squidBiomeTextureMapper2 = new BiomeTextureMapper(Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Ocean", "563"),
            new AbstractMap.SimpleEntry<>("Beach", "89"),
            new AbstractMap.SimpleEntry<>("Ice", "359"),
            new AbstractMap.SimpleEntry<>("Grassland", "286"),
            new AbstractMap.SimpleEntry<>("Tundra", "1062"),
            new AbstractMap.SimpleEntry<>("Woodland", "1355"),
            new AbstractMap.SimpleEntry<>("Savannah", "353"),
            new AbstractMap.SimpleEntry<>("Desert", "298"),
            new AbstractMap.SimpleEntry<>("default", "893")
    ), Utility.TEXTURE_ATLAS);

}
