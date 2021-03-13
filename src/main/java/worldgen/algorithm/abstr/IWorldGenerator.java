package worldgen.algorithm.abstr;

import worldgen.output.WorldMap;

public interface IWorldGenerator {

    WorldMap generateTerrain();

    WorldMap generateRivers();

    WorldMap generateFactions();


}
