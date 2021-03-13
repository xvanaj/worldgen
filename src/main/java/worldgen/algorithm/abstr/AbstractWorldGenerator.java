package worldgen.algorithm.abstr;

import lombok.Getter;
import squidpony.squidmath.*;
import worldgen.input.MapGeneratorInput;
import worldgen.output.WorldMap;

@Getter
public abstract class AbstractWorldGenerator implements IWorldGenerator {

    protected final StatefulRNG rng;

    //input and output
    protected final MapGeneratorInput input;
    protected final WorldMap worldMap;

    public AbstractWorldGenerator(MapGeneratorInput input) {
        this.input = input;
        this.rng = new StatefulRNG();
        this.worldMap = new WorldMap();
    }

    public WorldMap generate() {
        generateTerrain();
        generateRivers();
        generateFactions(); //contains generateRaces?

        //generatePlacesOfInterest()
        //generateResources()
        //generateItemTypes
        //generateMagicalItems
        //generateSpells
        //generateSkills/classes?
        //generateMythicalMonsters
        //generateMonsterTypes
        //generateMyths
        //generateGatesBetweenWorlds

        return worldMap;
    }

    protected void calculateBitmask(final boolean withCorners) {
        final int[][] biomesData = worldMap.getBiomesData();
        final String[] biomeNameTable = worldMap.getBiomeNameTable();

        int width = biomesData.length;
        int height = biomesData[0].length;

        final int[][] bitmaskData = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height ; y++) {
                if (withCorners) {
                    bitmaskData[x][y] = getBiomeBitmask8(x, y, biomesData, biomeNameTable, width, height);
                } else {
                    bitmaskData[x][y] = getBiomeBitmask4(x, y, biomesData, biomeNameTable, width, height);
                }
            }
        }

        worldMap.setBitmaskData(bitmaskData);
    }

    private int getBiomeBitmask8(int x, int y, int[][] biomesData, String[] biomeNameTable, int width, int height) {
        int count = 0;

        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x][y+1 == height ? 0 : y+1]]) count += 2;
        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x-1 < 0 ? width - 1 : x-1][y]]) count += 8;
        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x+1  == width ? 0 : x+1 ][y]]) count += 16;
        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x][y-1 < 0 ? height - 1 : y-1]]) count += 64;

        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x-1 < 0 ? width - 1 : x-1][y+1 == height ? 0 : y+1]]) count += 1;
        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x+1  == width ? 0 : x+1 ][y+1 == height ? 0 : y+1]]) count += 4;
        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x-1 < 0 ? width - 1 : x-1][y-1 < 0 ? height - 1 : y-1]]) count += 32;
        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x+1  == width ? 0 : x+1 ][y-1 < 0 ? height - 1 : y-1]]) count += 128;

        return count;
    }

    private int getBiomeBitmask4(int x, int y, int[][] biomesData, String[] biomeNameTable, int width, int height) {
        int count = 0;

        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x][y+1 == height ? 0 : y+1]]) count += 1;
        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x-1 < 0 ? width - 1 : x-1][y]]) count += 2;
        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x+1  == width ? 0 : x+1 ][y]]) count += 4;
        if (biomeNameTable[biomesData[x][y]] == biomeNameTable[biomesData[x][y-1 < 0 ? height - 1 : y-1]]) count += 8;

        return count;
    }


}

