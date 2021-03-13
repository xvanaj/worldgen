package view;

import editor.EditorObserver;

public interface MapStageObserver {

    enum MapStageEvent {
        MOUSE_MOVED
    }

    void onNotify(final MapStageObserver.MapStageEvent event, final Object... eventAgs);
}
