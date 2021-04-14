package com.vana.view;

public interface MapStageObservable {

    void addObserver(MapStageObserver observer);
    void removeObserver(MapStageObserver observer);
    void removeAllObservers();
    void notify(final MapStageObserver.MapStageEvent event, Object... values);
    
}
