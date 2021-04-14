package com.vana.renderer;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vana.renderer.api.OrthoMapRenderer;
import com.vana.util.Utility;
import com.vana.worldgen.output.WorldMap;

public class SpriteBatchIsoMapRenderer extends OrthoMapRenderer {

    public SpriteBatchIsoMapRenderer(WorldMap worldMap, Stage stage) {
        super(worldMap, stage);
    }

    @Override
    protected void drawTerrain() {
        if (drawTerrain) {
            for (int i = width - 1; i >= 0; i--) {
                for (int j = height - 1; j >= 0; j--) {
                    batch.draw(Utility.SIMPLE_TEXTURE_ATLAS.findRegion("0"),
                            -tileHeight + (i - j) * tileWidth / 2,
                            (i + j) * tileHeight / 2 /*+ tile.getYOffset()*/);
                }
            }
        }
    }
}
