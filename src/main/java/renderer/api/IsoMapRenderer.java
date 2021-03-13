package renderer.api;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import squidpony.squidgrid.gui.gdx.DefaultResources;
import worldgen.output.WorldMap;

public abstract class IsoMapRenderer extends AbstractMapRenderer {

    public IsoMapRenderer(WorldMap worldMap, Stage stage) {
        super(worldMap, stage);
    }

    protected void drawXYCoords() {
        BitmapFont smoothFont = DefaultResources.getSmoothFont();

        //coords grid
        for (int w = 0; w < width; w++) {
            smoothFont.draw(batch, String.valueOf(w), 19 + w * tileHeight, 8 + w * tileWidth);
        }

        for (int h = 0; h < height; h++) {
            smoothFont.draw(batch, String.valueOf(h),-19 - h * tileHeight, 8 + h * tileHeight);
        }
    }

    @Override
    public void drawTileBorders(int coordX, int coordY) {

    }
}
