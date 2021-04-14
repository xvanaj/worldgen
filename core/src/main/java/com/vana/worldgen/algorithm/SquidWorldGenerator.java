package com.vana.worldgen.algorithm;

import com.vana.util.LoggingUtils;
import com.vana.worldgen.algorithm.abstr.AbstractWorldGenerator;
import com.vana.worldgen.input.MapGeneratorInput;
import com.vana.worldgen.output.WorldMap;
import squidpony.squidgrid.mapping.WorldMapGenerator;
import squidpony.squidmath.FastNoise;

public class SquidWorldGenerator extends AbstractWorldGenerator {

    public SquidWorldGenerator(MapGeneratorInput input) {
        super(input);
    }

    @Override
    public WorldMap generateTerrain() {
        LoggingUtils.startCounting();

        //generate map (noise)
        final WorldMapGenerator worldMapGenerator = new WorldMapGenerator.TilingMap(input.getSeed(), input.getWIDTH(), input.getHEIGHT()
                //, new FastNoise(0x1337CAFE, 1f, FastNoise.SIMPLEX, 1));
                , new FastNoise(0x1337CAFE, 0.8f, FastNoise.SIMPLEX, 2));
        worldMapGenerator.generate(input.getSeed());
        LoggingUtils.logWithTime("Worldmap generation");

        //generate biomes
        final WorldMapGenerator.SimpleBiomeMapper simpleBiomeMapper = new WorldMapGenerator.SimpleBiomeMapper();
        simpleBiomeMapper.makeBiomes(worldMapGenerator);

        LoggingUtils.logWithTime("Biomes generation");
        worldMap.setWorldMapGenerator(worldMapGenerator);
        worldMap.setBiomeMapper(simpleBiomeMapper);
        worldMap.setBiomesData(simpleBiomeMapper.biomeCodeData);
        worldMap.setBiomeNameTable(simpleBiomeMapper.getBiomeNameTable());

        return worldMap;
    }

    @Override
    public WorldMap generateRivers() {
        return worldMap;
    }

    @Override
    public WorldMap generateFactions() {
        return worldMap;
    }

}