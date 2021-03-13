package worldgen.input;

import com.badlogic.gdx.graphics.Color;

import lombok.Getter;
import lombok.Setter;
import squidpony.squidmath.OrderedMap;

import java.util.*;

@Getter
@Setter
public class MapGeneratorInput {

    //seeds
    //public long seed = 0l;
    public long seed = new Random().nextInt();
    public long treesSeed;
    public long moistureSeed;
    public long heightSeed;
    public long ridgedHeightSeed;
    public long heatSeed;
    public long wildernesssSeed;
    public long objectsSeed = 0;

    //main input
/*    public MapGenerationType terrainGenerationType = MapGenerationType.SQUID_TILING;
    public MapGenerationType heatAndMoistureGenerationType = MapGenerationType.SQUID_TILING;
    public PlanetBiomeType planetBiomeType = PlanetBiomeType.EARTHLIKE;
    public PlanetSizeType planetSizeType = PlanetSizeType.TEST;
    public WaterCoverageType waterCoverageType = WaterCoverageType.AVERAGE;
    public PlanetHeightType planetHeightType = PlanetHeightType.AVERAGE;*/
    
    //common
    public String name;
    public int moons = 1;
    public float gravityG = 1f;

    public float timeToDawn = 2;
    public float timeToDusk = 2;

    public float hoursInDay = 24;
    public int daysInWeek = 7;
    public int daysInMonth = 30;
    public int monthsInYear = 12;

    public float dawnHour = 7;
    public float dayHour = 8;
    public float duskHour = 19;
    public float nightHour = 20;

    public boolean generateRivers = true;
    public boolean generateCanyons = true;
    public boolean generateFactions = true;
    public boolean fillDepressions = true;
    public boolean normalizeValues = false;
    public boolean calculateMaxHeightAutomatically = true;

    public int terrainOctaves = 6;
    public int terrainRidgeOctaves = 6;
    public double terrainFrequency = 1.25;

    public int heatOctaves = 4;
    public double heatFrequency = 3.0;

    public int moistureOctaves = 4;
    public double moistureFrequency = 3.0;

    public double terrainNoiseScale = 1;
    public double heatNoiseScale = 1;
    public double moistureNoiseScale = 1;

    //squid noise parameters->
    public double terrainFreq = 0.95, terrainRidgedFreq = 2.6, heatFreq = 2.1,
            moistureFreq = 2.125, otherFreq = 3.375;
    public double octavesMultiplier = 1;
    public double landModifier = 1;
    public double heatModifier = 1;

    public int WIDTH = 128;
    public int HEIGHT = 128;
    public int allowedMaxHeightLevel = Integer.MAX_VALUE;

    public int numFactions = 6;
    public double treesPercent = 0.3d;
    public double objectsPercent = 0.01d;

    public double DeepWater = 0.2;
    public double MediumWater = 0.3;
    public double ShallowWater = 0.4;
    public double CoastalWater = 0.48;
    public double Sand = 0.5;
    public double Grass = 0.7;
    public double Forest = 0.8;
    public double Rock = 0.9;

    public double ColdestValue = 0.15;
    public double ColderValue = 0.25;
    public double ColdValue = 0.40;
    public double WarmValue = 0.65;
    public double WarmerValue = 0.85;

/*
    public double DryerValue = 0.15;
    public double DryValue = 0.30;
    public double WetValue = 0.45;
    public double WetterValue = 0.60;
    public double WettestValue = 0.75;*/

    /*public double ColdestValue = 0.05;
    public double ColderValue = 0.18;
    public double ColdValue = 0.4;
    public double WarmValue = 0.6;
    public double WarmerValue = 0.8;*/

}
