package com.vana;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.vana.editor.EditorHud;
import com.vana.editor.EditorObserver;
import com.vana.renderer.LPCStyleRenderer;
import com.vana.renderer.api.IMapRenderer;
import com.vana.util.SaveLoadUtils;
import com.vana.util.Utility;
import com.vana.view.MapStage;
import com.vana.worldgen.algorithm.SquidWorldGenerator;
import com.vana.worldgen.input.MapGeneratorInput;
import com.vana.worldgen.output.WorldMap;
import squidpony.squidgrid.gui.gdx.SquidInput;
import squidpony.squidgrid.mapping.WorldMapGenerator;

public class MapScreen extends ApplicationAdapter implements EditorObserver {

    private long seed = 0l;
    private int SIZE = 64;

    private MapStage stage;
    private SquidInput input;

    private IMapRenderer mapRenderer;
    private WorldMap worldMap;
    private ShapeRenderer shapeRenderer;

    //editor
    private TextureAtlas.AtlasRegion editorCursorTexture;
    private EditorHud editorHud;

    private void regenerateMap() {
        MapGeneratorInput mgi = new MapGeneratorInput();

        mgi.setWIDTH(SIZE);
        mgi.setHEIGHT(SIZE);
        mgi.seed = seed++;

        regenerateMap(mgi);
    }

    private void regenerateMap(final MapGeneratorInput mgi) {
        worldMap = new SquidWorldGenerator(mgi).generate();
        mapRenderer = new LPCStyleRenderer(worldMap, stage);

        if (editorHud != null) {
            editorHud.dispose();
        }
        editorHud = new EditorHud(worldMap);
        editorHud.addObserver(this);

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
                        regenerateMap();
                        break;
                    case SquidInput.INSERT:
                        worldMap.getWorldMapGenerator().zoomIn(1, SIZE / 4, SIZE / 4);
                        worldMap.getBiomeMapper().makeBiomes(worldMap.getWorldMapGenerator());
                        worldMap.setBiomesData(((WorldMapGenerator.SimpleBiomeMapper) worldMap.getBiomeMapper()).biomeCodeData);
                        worldMap.setBiomeNameTable(worldMap.getBiomeMapper().getBiomeNameTable());
                        mapRenderer = new LPCStyleRenderer(worldMap, stage);
                        break;
                    case SquidInput.FORWARD_DELETE:
                        worldMap.getWorldMapGenerator().zoomOut(1, SIZE, SIZE);
                        worldMap.getBiomeMapper().makeBiomes(worldMap.getWorldMapGenerator());
                        worldMap.setBiomesData(((WorldMapGenerator.SimpleBiomeMapper) worldMap.getBiomeMapper()).biomeCodeData);
                        worldMap.setBiomeNameTable(worldMap.getBiomeMapper().getBiomeNameTable());
                        mapRenderer = new LPCStyleRenderer(worldMap, stage);
                        break;
                }
            }
        });

        regenerateMap();

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
    public void onNotify(EditorObserver.EditorEvent event, String... eventArgs) {
        if (event.equals(EditorObserver.EditorEvent.TILE_SELECTED)) {
            editorCursorTexture = Utility.SIMPLE_TEXTURE_ATLAS.findRegion(eventArgs[0]);
        } else if (event.equals(EditorObserver.EditorEvent.SAVE_WORLD)) {
            SaveLoadUtils.save(worldMap.getMapGeneratorInput(), "save/input");
        } else if (event.equals(EditorObserver.EditorEvent.LOAD_WORLD)) {
            MapGeneratorInput input = (MapGeneratorInput) SaveLoadUtils.load(MapGeneratorInput.class, "save/input");
            regenerateMap(input);
        }
    }
}
