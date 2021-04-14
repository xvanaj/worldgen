package com.vana.worldgen.algorithm;

import com.vana.util.ArrayUtils;
import com.vana.util.LoggingUtils;
import com.vana.worldgen.algorithm.abstr.AbstractWorldGenerator;
import com.vana.worldgen.input.MapGeneratorInput;
import com.vana.worldgen.output.MapData;
import com.vana.worldgen.output.WorldMap;
import squidpony.squidmath.FastNoise;

public class CivWorldGenerator extends AbstractWorldGenerator {

    public CivWorldGenerator(MapGeneratorInput input) {
        super(input);
    }

    @Override
    public WorldMap generateTerrain() {
        LoggingUtils.startCounting();

        final FastNoise fastNoise = new FastNoise((int) input.getSeed());
        float[][] noise = new float[input.getWIDTH()][input.getHEIGHT()];
        float min = Float.MIN_VALUE;
        float max = Float.MAX_VALUE;

        for (int i = 0; i < input.getWIDTH(); i++) {
            for (int j = 0; j < input.getHEIGHT(); j++) {
                float cellular = fastNoise.getCubicFractal(i, j);
                noise[i][j] = cellular;
                if (cellular > max) {
                    max = cellular;
                }
                if (cellular < min) {
                    min = cellular;
                }
            }
        }

        if (input.getWIDTH() < 257) {
            ArrayUtils.printArray(noise);
        }

        //generate biomes


        LoggingUtils.logWithTime("Biomes generation");
        worldMap.setHeightData(new MapData(input.WIDTH, input.HEIGHT));
        worldMap.getHeightData().setData(ArrayUtils.toDouble(noise));
        /*worldMap.setBiomesData(simpleBiomeMapper.biomeCodeData);
        worldMap.setBiomeNameTable(simpleBiomeMapper.getBiomeNameTable());*/

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
