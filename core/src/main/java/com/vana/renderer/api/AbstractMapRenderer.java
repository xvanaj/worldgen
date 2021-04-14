package com.vana.renderer.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vana.worldgen.output.WorldMap;

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
