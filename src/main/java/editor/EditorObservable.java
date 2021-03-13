package editor;

public interface EditorObservable {

    void addObserver(EditorObserver observer);
    void removeObserver(EditorObserver observer);
    void removeAllObservers();
    void notify(final EditorObserver.EditorEvent event, String... values);
    
}
