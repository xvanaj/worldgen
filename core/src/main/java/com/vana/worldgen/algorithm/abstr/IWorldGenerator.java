package com.vana.worldgen.algorithm.abstr;

import com.vana.worldgen.output.WorldMap;

public interface IWorldGenerator {

    WorldMap generateTerrain();

    WorldMap generateRivers();

    WorldMap generateFactions();


}
