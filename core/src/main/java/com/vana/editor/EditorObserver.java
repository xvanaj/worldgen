package com.vana.editor;

public interface EditorObserver {

    enum EditorEvent {
        SAVE_WORLD, LOAD_WORLD, TILE_SELECTED
    }

    void onNotify(final EditorEvent event, final String... eventAgs);

}
