package editor;

public interface EditorObserver {

    enum EditorEvent {
        TILE_SELECTED
    }

    void onNotify(final EditorEvent event, final String... eventAgs);

}
