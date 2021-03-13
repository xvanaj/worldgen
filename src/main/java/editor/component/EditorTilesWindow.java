package editor.component;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisImageButton;
import editor.EditorObservable;
import editor.EditorObserver;
import view.component.BlockingVisWindow;
import util.Utility;

import java.util.ArrayList;
import java.util.List;

public class EditorTilesWindow extends BlockingVisWindow implements EditorObservable {

    private List<EditorObserver> observers;

    public EditorTilesWindow(String title) {
        super(title);
        setKeepWithinStage(false);
        observers = new ArrayList<>();

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

    @Override
    public void addObserver(EditorObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(EditorObserver observer) {
        observers.remove(observer);

    }

    @Override
    public void removeAllObservers() {
        observers.clear();
    }

    @Override
    public void notify(EditorObserver.EditorEvent event, String... values) {
        for(EditorObserver observer: observers) {
            observer.onNotify(event, values);
        }
    }

    private class TextureRegionButton extends VisImageButton {

        public TextureRegionButton(TextureRegionDrawable image) {
            super(image);

            this.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    EditorTilesWindow.this.notify(EditorObserver.EditorEvent.TILE_SELECTED,
                            ((TextureAtlas.AtlasRegion)image.getRegion()).name);
                }
            });
        }
    }
}
