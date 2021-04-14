package com.vana.editor.component;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.vana.editor.EditorHud;
import com.vana.editor.EditorObserver;
import com.vana.util.Utility;
import com.vana.view.component.BlockingVisWindow;

public class EditorTilesWindow extends BlockingVisWindow {

    private EditorHud editorHud;

    public EditorTilesWindow(EditorHud editor, String title) {
        super(title);
        this.editorHud = editor;
        setKeepWithinStage(false);

        int count = 0;
        final Array<TextureAtlas.AtlasRegion> regions = Utility.SIMPLE_TEXTURE_ATLAS.getRegions();

        for (TextureAtlas.AtlasRegion region : regions) {
            count ++;
            if (count >= 20) {
                count = 0;
                row();
            }

            TextureRegionButton button = new TextureRegionButton(new TextureRegionDrawable(region));

            add(button);
            addActor(button);
        }

        pack();
    }

    private class TextureRegionButton extends VisImageButton {

        public TextureRegionButton(TextureRegionDrawable image) {
            super(image);

            this.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    editorHud.notify(EditorObserver.EditorEvent.TILE_SELECTED,
                            ((TextureAtlas.AtlasRegion)image.getRegion()).name);
                }
            });
        }
    }
}
