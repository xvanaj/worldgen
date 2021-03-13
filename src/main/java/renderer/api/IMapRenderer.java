package renderer.api;

import squidpony.squidmath.Coord;
import worldgen.output.WorldMap;

public interface IMapRenderer {

    void putMap();

    void drawPolygon(float[] vertices);

    void drawTileBorders(int coordX, int coordY);

    void dispose();
}
