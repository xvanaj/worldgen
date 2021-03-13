package worldgen.output;

import com.badlogic.gdx.graphics.Texture;
import lombok.Getter;
import lombok.Setter;
import squidpony.FakeLanguageGen;
import squidpony.squidai.DijkstraMap;
import squidpony.squidgrid.mapping.WorldMapGenerator;
import squidpony.squidgrid.mapping.styled.Tile;
import squidpony.squidmath.AStarSearch;
import squidpony.squidmath.Coord;
import squidpony.squidmath.GreasedRegion;
import squidpony.squidmath.OrderedMap;
import worldgen.input.MapGeneratorInput;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
}
