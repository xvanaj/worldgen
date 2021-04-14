package com.vana.renderer.api;

public interface IMapRenderer {

    void putMap();

    void drawPolygon(float[] vertices);

    void drawTileBorders(int coordX, int coordY);

    void dispose();
}
