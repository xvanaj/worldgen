package com.vana.worldgen.output;

import com.vana.worldgen.input.MapGeneratorInput;
import squidpony.squidgrid.mapping.WorldMapGenerator;

public class WorldMap {

    private MapData heightData;
    private MapData heatData;
    private MapData moistureData;
    private MapData hostilityData;

    private int[][] biomesData;
    private int[][] bitmaskData;
    private String[] biomeNameTable;

    //squid
    private WorldMapGenerator worldMapGenerator;
    private WorldMapGenerator.BiomeMapper biomeMapper;

    public MapGeneratorInput mapGeneratorInput;
/*
    //mapdata
    public MapIntData heightLevelData;

    double[][] initialHeatData;

    public Tile[][] tiles;
    public char[][] politicalMap;
    public char[][] waterLandMap;
    public GreasedRegion waterlandMapGreased;

    public List<River> riverPaths;
    public List<RiverGroup> riverGroups;
    public List<NaturePattern> naturePatterns;
    public List<Tower> towers;

    //political
    public List<Faction> factions;
    public OrderedMap<Character, FakeLanguageGen> atlasLang = new OrderedMap<>();
    public Map<Character, SColor> factionColorMap;
    public final OrderedMap<Character, String> atlas = new OrderedMap<>(16);

    public List<List<Coord>> roads;

    public List<TileGroup> waters = new ArrayList<>();
    public List<TileGroup> lands = new ArrayList<>();
    public List<List<Coord>> landRegions;
    public List<BiomeRegion> biomeRegions;

    public List<Coord> ocean;
    public List<List<Coord>> lakes;
    public GreasedRegion coastline;

    public OrderedMap<Coord, String> cities;
    public OrderedMap<Coord, String> dungeons;

    public TreesLayer treesLayer;
    public FactionsLayer factionsLayer;

    public WorldTime worldTime;
    //events
    public Map<Long, Event> events = new OrderedMap<>();

    //map images
    public Texture heightMapTexture;
    public Texture waterMapTexture;
    public Texture heatMapTexture;
    public Texture moistureMapTexture;
    public Texture politicalMapTexture;
    public Texture biomeMapTexture;
    public Texture hostilityMapTexture;

    //statistics
    public int[][] heatMoistureCombinationCounts;
    public EnumMap<BiomeType, Integer> biomeTypeCounts;
    public int riverCount = 0;
    public int riverPathCount = 0;
    public int landTilesCount;

    // effects
    public List<Effect> gifEffects = new ArrayList<>();

    //entities
    public Player player;
    public List<Entity> entities;
    public List<Tornado> tornadoes = new ArrayList<>();

    public Tile[][] getTiles() {
        return tiles;
    }

    //temp
    public AStarSearch aStarSearch;
    public DijkstraMap dijkstraMap;
    public DijkstraMap getToPlayer;


    public List<Polygon> waterBodies = new ArrayList<>();


    public void act() {
    }
*/

    public MapData getHeightData() {
        return heightData;
    }

    public void setHeightData(MapData heightData) {
        this.heightData = heightData;
    }

    public MapData getHeatData() {
        return heatData;
    }

    public void setHeatData(MapData heatData) {
        this.heatData = heatData;
    }

    public MapData getMoistureData() {
        return moistureData;
    }

    public void setMoistureData(MapData moistureData) {
        this.moistureData = moistureData;
    }

    public MapData getHostilityData() {
        return hostilityData;
    }

    public void setHostilityData(MapData hostilityData) {
        this.hostilityData = hostilityData;
    }

    public int[][] getBiomesData() {
        return biomesData;
    }

    public void setBiomesData(int[][] biomesData) {
        this.biomesData = biomesData;
    }

    public int[][] getBitmaskData() {
        return bitmaskData;
    }

    public void setBitmaskData(int[][] bitmaskData) {
        this.bitmaskData = bitmaskData;
    }

    public String[] getBiomeNameTable() {
        return biomeNameTable;
    }

    public void setBiomeNameTable(String[] biomeNameTable) {
        this.biomeNameTable = biomeNameTable;
    }

    public WorldMapGenerator getWorldMapGenerator() {
        return worldMapGenerator;
    }

    public void setWorldMapGenerator(WorldMapGenerator worldMapGenerator) {
        this.worldMapGenerator = worldMapGenerator;
    }

    public WorldMapGenerator.BiomeMapper getBiomeMapper() {
        return biomeMapper;
    }

    public void setBiomeMapper(WorldMapGenerator.BiomeMapper biomeMapper) {
        this.biomeMapper = biomeMapper;
    }

    public MapGeneratorInput getMapGeneratorInput() {
        return mapGeneratorInput;
    }

    public void setMapGeneratorInput(MapGeneratorInput mapGeneratorInput) {
        this.mapGeneratorInput = mapGeneratorInput;
    }
}
