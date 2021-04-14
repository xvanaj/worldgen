package com.vana.renderer;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.vana.util.Utility;

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
        return getBiomeTexture(biomeCode, biomeBitmask, null);
    }

    public TextureAtlas.AtlasRegion getBiomeTexture(final String biomeCode, final int biomeBitmask, final String suffix) {
        final String textureName = biomeTextureMapping.get(biomeCode);

        //first try to search texture with suffix
        if (cache.get(biomeCode + biomeBitmask + suffix) == null) {

            TextureAtlas.AtlasRegion region = textureAtlas.findRegion(textureName + biomeBitmask + suffix);
            if (region != null) {
                cache.put(biomeCode + biomeBitmask + suffix, region);
                return region;
            }
        } else {
            return cache.get(biomeCode + biomeBitmask + suffix);
        }

        //if not found, return regular
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

    public static BiomeTextureMapper squidBiomeTextureMapper;
    static {
        HashMap<String, String> mappings = new HashMap<>();

        mappings.put("Ocean", "water/water-");
        mappings.put("Beach", "sand/sand-");
        mappings.put("Ice", "snow/snow-");
        mappings.put("Grassland", "bricks_grey/bricks_grey-");
        mappings.put("Tundra", "dirt_night/dirt_night-");
        mappings.put("Woodland", "dirt_grey/dirt_grey-");
        mappings.put("Savannah", "dirt2/dirt2-");
        mappings.put("Desert", "desert/desert-");
        mappings.put("SeasonalForest", "grass/grass-");
        mappings.put("TemperateRainforest", "tallgrass/tallgrass-");
        mappings.put("BorealForest", "swamp/swamp-");
        mappings.put("Rocky", "lavarock/lavarock-");
        mappings.put("Savanna", "savanna/savanna-");
        mappings.put("TropicalRainforest", "rock_brown/rock_brown-");
        mappings.put("default", "redsand/redsand-");

        squidBiomeTextureMapper = new BiomeTextureMapper(mappings, Utility.SIMPLE_TEXTURE_ATLAS);
    }

}
