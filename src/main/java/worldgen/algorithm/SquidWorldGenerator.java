package worldgen.algorithm;

import squidpony.squidgrid.mapping.WorldMapGenerator;
import squidpony.squidmath.FastNoise;
import util.LoggingUtils;
import worldgen.algorithm.abstr.AbstractWorldGenerator;
import worldgen.input.MapGeneratorInput;
import worldgen.output.WorldMap;

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
                , new FastNoise(0x1337CAFE, 0.8f, FastNoise.SIMPLEX, 4));
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

        calculateBitmask(true);

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