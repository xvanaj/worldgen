package com.vana.editor.component;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.vana.editor.EditorHud;
import com.vana.editor.EditorObserver;
import com.vana.view.component.BlockingVisWindow;

public class WorldGenWindow extends BlockingVisWindow {

    private final EditorHud editorHud;

    public WorldGenWindow(EditorHud editorHud, String title) {
        super(title);
        this.editorHud = editorHud;

        VisTextButton saveWorldButton = new VisTextButton("Save");
        saveWorldButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                editorHud.notify(EditorObserver.EditorEvent.SAVE_WORLD);
            }
        });
        add(saveWorldButton);
        VisTextButton loadWorldButton = new VisTextButton("Load");
        loadWorldButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                editorHud.notify(EditorObserver.EditorEvent.LOAD_WORLD);
            }
        });
        add(loadWorldButton);

    }

}
