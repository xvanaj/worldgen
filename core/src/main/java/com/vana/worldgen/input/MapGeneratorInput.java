package com.vana.worldgen.input;

import java.util.Random;

public class MapGeneratorInput {

    //seeds
    //public long seed = 0l;
    public long seed = new Random().nextInt();
/*    public long treesSeed;
    public long moistureSeed;
    public long heightSeed;
    public long ridgedHeightSeed;
    public long heatSeed;
    public long wildernesssSeed;
    public long objectsSeed = 0;*/

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

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoons() {
        return moons;
    }

    public void setMoons(int moons) {
        this.moons = moons;
    }

    public float getGravityG() {
        return gravityG;
    }

    public void setGravityG(float gravityG) {
        this.gravityG = gravityG;
    }

    public float getTimeToDawn() {
        return timeToDawn;
    }

    public void setTimeToDawn(float timeToDawn) {
        this.timeToDawn = timeToDawn;
    }

    public float getTimeToDusk() {
        return timeToDusk;
    }

    public void setTimeToDusk(float timeToDusk) {
        this.timeToDusk = timeToDusk;
    }

    public float getHoursInDay() {
        return hoursInDay;
    }

    public void setHoursInDay(float hoursInDay) {
        this.hoursInDay = hoursInDay;
    }

    public int getDaysInWeek() {
        return daysInWeek;
    }

    public void setDaysInWeek(int daysInWeek) {
        this.daysInWeek = daysInWeek;
    }

    public int getDaysInMonth() {
        return daysInMonth;
    }

    public void setDaysInMonth(int daysInMonth) {
        this.daysInMonth = daysInMonth;
    }

    public int getMonthsInYear() {
        return monthsInYear;
    }

    public void setMonthsInYear(int monthsInYear) {
        this.monthsInYear = monthsInYear;
    }

    public float getDawnHour() {
        return dawnHour;
    }

    public void setDawnHour(float dawnHour) {
        this.dawnHour = dawnHour;
    }

    public float getDayHour() {
        return dayHour;
    }

    public void setDayHour(float dayHour) {
        this.dayHour = dayHour;
    }

    public float getDuskHour() {
        return duskHour;
    }

    public void setDuskHour(float duskHour) {
        this.duskHour = duskHour;
    }

    public float getNightHour() {
        return nightHour;
    }

    public void setNightHour(float nightHour) {
        this.nightHour = nightHour;
    }

    public boolean isGenerateRivers() {
        return generateRivers;
    }

    public void setGenerateRivers(boolean generateRivers) {
        this.generateRivers = generateRivers;
    }

    public boolean isGenerateCanyons() {
        return generateCanyons;
    }

    public void setGenerateCanyons(boolean generateCanyons) {
        this.generateCanyons = generateCanyons;
    }

    public boolean isGenerateFactions() {
        return generateFactions;
    }

    public void setGenerateFactions(boolean generateFactions) {
        this.generateFactions = generateFactions;
    }

    public boolean isFillDepressions() {
        return fillDepressions;
    }

    public void setFillDepressions(boolean fillDepressions) {
        this.fillDepressions = fillDepressions;
    }

    public boolean isNormalizeValues() {
        return normalizeValues;
    }

    public void setNormalizeValues(boolean normalizeValues) {
        this.normalizeValues = normalizeValues;
    }

    public boolean isCalculateMaxHeightAutomatically() {
        return calculateMaxHeightAutomatically;
    }

    public void setCalculateMaxHeightAutomatically(boolean calculateMaxHeightAutomatically) {
        this.calculateMaxHeightAutomatically = calculateMaxHeightAutomatically;
    }

    public int getTerrainOctaves() {
        return terrainOctaves;
    }

    public void setTerrainOctaves(int terrainOctaves) {
        this.terrainOctaves = terrainOctaves;
    }

    public int getTerrainRidgeOctaves() {
        return terrainRidgeOctaves;
    }

    public void setTerrainRidgeOctaves(int terrainRidgeOctaves) {
        this.terrainRidgeOctaves = terrainRidgeOctaves;
    }

    public double getTerrainFrequency() {
        return terrainFrequency;
    }

    public void setTerrainFrequency(double terrainFrequency) {
        this.terrainFrequency = terrainFrequency;
    }

    public int getHeatOctaves() {
        return heatOctaves;
    }

    public void setHeatOctaves(int heatOctaves) {
        this.heatOctaves = heatOctaves;
    }

    public double getHeatFrequency() {
        return heatFrequency;
    }

    public void setHeatFrequency(double heatFrequency) {
        this.heatFrequency = heatFrequency;
    }

    public int getMoistureOctaves() {
        return moistureOctaves;
    }

    public void setMoistureOctaves(int moistureOctaves) {
        this.moistureOctaves = moistureOctaves;
    }

    public double getMoistureFrequency() {
        return moistureFrequency;
    }

    public void setMoistureFrequency(double moistureFrequency) {
        this.moistureFrequency = moistureFrequency;
    }

    public double getTerrainNoiseScale() {
        return terrainNoiseScale;
    }

    public void setTerrainNoiseScale(double terrainNoiseScale) {
        this.terrainNoiseScale = terrainNoiseScale;
    }

    public double getHeatNoiseScale() {
        return heatNoiseScale;
    }

    public void setHeatNoiseScale(double heatNoiseScale) {
        this.heatNoiseScale = heatNoiseScale;
    }

    public double getMoistureNoiseScale() {
        return moistureNoiseScale;
    }

    public void setMoistureNoiseScale(double moistureNoiseScale) {
        this.moistureNoiseScale = moistureNoiseScale;
    }

    public double getTerrainFreq() {
        return terrainFreq;
    }

    public void setTerrainFreq(double terrainFreq) {
        this.terrainFreq = terrainFreq;
    }

    public double getTerrainRidgedFreq() {
        return terrainRidgedFreq;
    }

    public void setTerrainRidgedFreq(double terrainRidgedFreq) {
        this.terrainRidgedFreq = terrainRidgedFreq;
    }

    public double getHeatFreq() {
        return heatFreq;
    }

    public void setHeatFreq(double heatFreq) {
        this.heatFreq = heatFreq;
    }

    public double getMoistureFreq() {
        return moistureFreq;
    }

    public void setMoistureFreq(double moistureFreq) {
        this.moistureFreq = moistureFreq;
    }

    public double getOtherFreq() {
        return otherFreq;
    }

    public void setOtherFreq(double otherFreq) {
        this.otherFreq = otherFreq;
    }

    public double getOctavesMultiplier() {
        return octavesMultiplier;
    }

    public void setOctavesMultiplier(double octavesMultiplier) {
        this.octavesMultiplier = octavesMultiplier;
    }

    public double getLandModifier() {
        return landModifier;
    }

    public void setLandModifier(double landModifier) {
        this.landModifier = landModifier;
    }

    public double getHeatModifier() {
        return heatModifier;
    }

    public void setHeatModifier(double heatModifier) {
        this.heatModifier = heatModifier;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getAllowedMaxHeightLevel() {
        return allowedMaxHeightLevel;
    }

    public void setAllowedMaxHeightLevel(int allowedMaxHeightLevel) {
        this.allowedMaxHeightLevel = allowedMaxHeightLevel;
    }

    public int getNumFactions() {
        return numFactions;
    }

    public void setNumFactions(int numFactions) {
        this.numFactions = numFactions;
    }

    public double getTreesPercent() {
        return treesPercent;
    }

    public void setTreesPercent(double treesPercent) {
        this.treesPercent = treesPercent;
    }

    public double getObjectsPercent() {
        return objectsPercent;
    }

    public void setObjectsPercent(double objectsPercent) {
        this.objectsPercent = objectsPercent;
    }

    public double getDeepWater() {
        return DeepWater;
    }

    public void setDeepWater(double deepWater) {
        DeepWater = deepWater;
    }

    public double getMediumWater() {
        return MediumWater;
    }

    public void setMediumWater(double mediumWater) {
        MediumWater = mediumWater;
    }

    public double getShallowWater() {
        return ShallowWater;
    }

    public void setShallowWater(double shallowWater) {
        ShallowWater = shallowWater;
    }

    public double getCoastalWater() {
        return CoastalWater;
    }

    public void setCoastalWater(double coastalWater) {
        CoastalWater = coastalWater;
    }

    public double getSand() {
        return Sand;
    }

    public void setSand(double sand) {
        Sand = sand;
    }

    public double getGrass() {
        return Grass;
    }

    public void setGrass(double grass) {
        Grass = grass;
    }

    public double getForest() {
        return Forest;
    }

    public void setForest(double forest) {
        Forest = forest;
    }

    public double getRock() {
        return Rock;
    }

    public void setRock(double rock) {
        Rock = rock;
    }

    public double getColdestValue() {
        return ColdestValue;
    }

    public void setColdestValue(double coldestValue) {
        ColdestValue = coldestValue;
    }

    public double getColderValue() {
        return ColderValue;
    }

    public void setColderValue(double colderValue) {
        ColderValue = colderValue;
    }

    public double getColdValue() {
        return ColdValue;
    }

    public void setColdValue(double coldValue) {
        ColdValue = coldValue;
    }

    public double getWarmValue() {
        return WarmValue;
    }

    public void setWarmValue(double warmValue) {
        WarmValue = warmValue;
    }

    public double getWarmerValue() {
        return WarmerValue;
    }

    public void setWarmerValue(double warmerValue) {
        WarmerValue = warmerValue;
    }
}
