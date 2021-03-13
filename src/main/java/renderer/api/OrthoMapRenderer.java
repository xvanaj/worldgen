package renderer.api;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import squidpony.squidgrid.gui.gdx.DefaultResources;
import worldgen.output.WorldMap;

public abstract class OrthoMapRenderer extends AbstractMapRenderer {

    public OrthoMapRenderer(WorldMap worldMap, Stage stage) {
        super(worldMap, stage);
    }

    protected void drawXYCoords() {
        BitmapFont smoothFont = DefaultResources.getSmoothFont();

        //coords grid
        for (int w = 0; w < width; w++) {
            smoothFont.draw(batch, String.valueOf(w), w * 32, 0);
        }

        for (int h = 0; h < height; h++) {
            smoothFont.draw(batch, String.valueOf(h), -32, h * 32);
        }
    }

    @Override
    protected void drawGrid() {

    }

    @Override
    public void drawTileBorders(int coordX, int coordY) {
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(coordX * tileWidth, coordY * tileHeight, tileWidth, tileHeight);
        shapeRenderer.end();
    }
}
