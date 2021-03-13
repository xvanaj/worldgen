package renderer.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import worldgen.output.WorldMap;

public abstract class AbstractMapRenderer implements IMapRenderer {

    //what is drawn?
    protected boolean scalingShader = false;
    protected boolean drawPolitical;
    protected final boolean showCities = true;
    protected boolean dungeonNames, cityNames = true;
    protected boolean drawRoads = true;
    protected boolean drawTrees = true;
    protected boolean drawGrid = false;
    protected boolean dayNightCycle = true;
    protected boolean lineOfSight = false;
    protected boolean drawObjects = true;
    protected boolean drawBiomeRegions;
    protected boolean drawCollisions;
    protected final boolean redrawPolitical = true;
    protected boolean drawTerrain = true;
    protected boolean drawWater = true;
    protected final boolean drawHeightType = false;

    //map related
    protected final WorldMap worldMap;
    protected int width;
    protected int height;
    protected int tileWidth = 32;
    protected int tileHeight = 32;

    //rendering related
    protected final ShapeRenderer shapeRenderer;
    protected final Stage stage;
    protected final Batch batch;
    protected final Camera camera;

    public AbstractMapRenderer(WorldMap worldMap, Stage stage) {
        this.worldMap = worldMap;
        this.width = worldMap.getBiomesData().length;
        this.height = worldMap.getBiomesData()[0].length;
        this.stage = stage;
        this.camera = stage.getCamera();
        this.batch = stage.getBatch();
        this.shapeRenderer = new ShapeRenderer();
    }

    public void putMap() {
        camera.update();
        stage.getViewport().apply(false);
        batch.setProjectionMatrix(camera.combined);
        batch.setColor(Color.WHITE);
        batch.begin();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        drawTerrain();

        drawXYCoords();
        drawGrid();

        batch.end();
        stage.draw();

    }

    @Override
    public void drawPolygon(final float[] vertices) {
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.polygon(vertices);
        shapeRenderer.end();
    }

    protected abstract void drawTerrain();

    protected abstract void drawXYCoords();

    protected abstract void drawGrid();

    @Override
    public void dispose() {

    }
}
