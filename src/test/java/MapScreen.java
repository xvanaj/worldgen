import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import editor.EditorHud;
import editor.EditorObserver;
import renderer.LPCStyleRenderer;
import renderer.LPCStyleRendererBack;
import squidpony.squidgrid.gui.gdx.SquidInput;
import squidpony.squidgrid.mapping.WorldMapGenerator;
import view.MapStage;
import util.Utility;
import worldgen.algorithm.SquidWorldGenerator;
import worldgen.input.MapGeneratorInput;
import worldgen.output.WorldMap;
import renderer.api.IMapRenderer;
import renderer.SpriteCacheOrthoMapRenderer;

public class MapScreen extends ApplicationAdapter implements EditorObserver {

    private long seed = 0l;
    private int SIZE = 512;

    private MapStage stage;
    private SquidInput input;

    private IMapRenderer mapRenderer;
    private WorldMap worldMap;
    private ShapeRenderer shapeRenderer;

    //editor
    private TextureAtlas.AtlasRegion editorCursorTexture;
    private EditorHud editorHud;

    private void setupTest() {
        MapGeneratorInput mgi = new MapGeneratorInput();
        mgi.setWIDTH(SIZE);
        mgi.setHEIGHT(SIZE);
        mgi.seed = seed++;

        worldMap = new SquidWorldGenerator(mgi).generate();
        mapRenderer = new LPCStyleRenderer(worldMap, stage);
        //mapRenderer = new SpriteCacheOrthoMapRenderer(worldMap, stage);

        if (editorHud != null) {
            editorHud.dispose();
        }
        editorHud = new EditorHud(worldMap);
        editorHud.getTilesWindow().addObserver(this);

        stage.addObserver(editorHud);
        stage.setWorldMap(worldMap);

        Gdx.input.setInputProcessor(new InputMultiplexer(editorHud.getStage(), stage, input));

    }

    @Override
    public void create() {
        VisUI.load();

        shapeRenderer = new ShapeRenderer();
        stage = new MapStage(new ScreenViewport(), null);

        input = new SquidInput(new SquidInput.KeyHandler() {
            @Override
            public void handle(char key, boolean alt, boolean ctrl, boolean shift) {
                switch (key) {
                    case SquidInput.ENTER:
                        setupTest();
                        break;
                    case SquidInput.INSERT:
                        worldMap.getWorldMapGenerator().zoomIn(1, SIZE / 4, SIZE / 4);
                        worldMap.getBiomeMapper().makeBiomes(worldMap.getWorldMapGenerator());
                        worldMap.setBiomesData(((WorldMapGenerator.SimpleBiomeMapper) worldMap.getBiomeMapper()).biomeCodeData);
                        worldMap.setBiomeNameTable(worldMap.getBiomeMapper().getBiomeNameTable());
                        mapRenderer = new SpriteCacheOrthoMapRenderer(worldMap, stage);
                        break;
                    case SquidInput.FORWARD_DELETE:
                        worldMap.getWorldMapGenerator().zoomOut(1, SIZE, SIZE);
                        worldMap.getBiomeMapper().makeBiomes(worldMap.getWorldMapGenerator());
                        worldMap.setBiomesData(((WorldMapGenerator.SimpleBiomeMapper) worldMap.getBiomeMapper()).biomeCodeData);
                        worldMap.setBiomeNameTable(worldMap.getBiomeMapper().getBiomeNameTable());
                        mapRenderer = new SpriteCacheOrthoMapRenderer(worldMap, stage);
                        break;
                }
            }
        });

        setupTest();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, false);
        stage.getViewport().apply();
        editorHud.resize(width, height);
    }

    @Override
    public void render() {
        if (input.hasNext()) {
            input.next();
        }

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond());

        stage.getBatch().end();
        mapRenderer.putMap();

        Vector2 v = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

/*        mapRenderer.drawPolygon(new float[]{
                (v.x - SIZE / 2) * ((OrthographicCamera) stage.getCamera()).zoom, (v.y - SIZE / 2) * ((OrthographicCamera) stage.getCamera()).zoom,
                (v.x - SIZE / 2) * ((OrthographicCamera) stage.getCamera()).zoom, (v.y + SIZE / 2) * ((OrthographicCamera) stage.getCamera()).zoom,
                (v.x + SIZE / 2) * ((OrthographicCamera) stage.getCamera()).zoom, (v.y + SIZE / 2) * ((OrthographicCamera) stage.getCamera()).zoom,
                (v.x + SIZE / 2) * ((OrthographicCamera) stage.getCamera()).zoom, (v.y - SIZE / 2) * ((OrthographicCamera) stage.getCamera()).zoom
        });*/

        mapRenderer.drawTileBorders((int)v.x / 32,(int)v.y / 32);

        if (editorCursorTexture != null) {
            stage.getBatch().begin();
            stage.getBatch().draw(editorCursorTexture, v.x-16, v.y-16);
            stage.getBatch().end();
        }

        editorHud.getStage().act();
        editorHud.getStage().draw();

        stage.act();
        stage.draw();
    }

    @Override
    public void onNotify(EditorEvent event, String... eventArgs) {
        if (event.equals(EditorEvent.TILE_SELECTED)) {
            editorCursorTexture = Utility.SIMPLE_TEXTURE_ATLAS.findRegion(eventArgs[0]);
        }
    }
}
