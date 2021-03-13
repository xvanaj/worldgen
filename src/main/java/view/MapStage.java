package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import view.component.ScrollingStage;
import worldgen.output.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class MapStage extends ScrollingStage implements MapStageObservable {

    private WorldMap worldMap;
    private boolean wrappedMap = false;
    private boolean fixBounds = false;

    private List<MapStageObserver> observers;

    public MapStage(ScreenViewport screenViewport, final WorldMap worldMap) {
        super(screenViewport);

        this.worldMap = worldMap;
        this.observers = new ArrayList<>();
    }

    @Override
    public boolean keyUp(int keyCode) {
        return super.keyUp(keyCode);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector2 v = screenToStageCoordinates(new Vector2(screenX, screenY));//todo memory leak?

        notify(MapStageObserver.MapStageEvent.MOUSE_MOVED, v.x, v.y);
        return super.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        boolean processed = super.touchDragged(screenX, screenY, pointer);

        float x = Gdx.input.getDeltaX();
        float y = Gdx.input.getDeltaY();

        OrthographicCamera camera = (OrthographicCamera) getCamera();

        final Vector3 unproject = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        //move camera
        camera.translate(-x * camera.zoom, y * camera.zoom);

        //fix bounds
        if (fixBounds) {
            fixBounds();
        }

        return processed;
    }

    private void fixBounds() {
        OrthographicCamera camera = (OrthographicCamera) getCamera();

        int bigWidth = worldMap.getBiomesData().length * 32;
        int bigHeight = worldMap.getBiomesData()[0].length * 32;

        if (getCamera().position.x < camera.viewportWidth * camera.zoom / 2) {
            getCamera().position.x = camera.viewportWidth * camera.zoom / 2;
        }
        if (getCamera().position.y < camera.viewportHeight * camera.zoom / 2) {
            getCamera().position.y = camera.viewportHeight * camera.zoom / 2;
        }
        if (getCamera().position.x + camera.viewportWidth * camera.zoom / 2 >  bigWidth) {
            getCamera().position.x = bigWidth - camera.viewportWidth * camera.zoom / 2;
        }
        if (getCamera().position.y + camera.viewportHeight * camera.zoom / 2 >  bigHeight) {
            getCamera().position.y = bigHeight - camera.viewportHeight * camera.zoom / 2;
        }
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
//scrolling works only when we are not on the message stage
        Vector2 v = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        zoomMap((int) amountY);
        return true;
    }

    @Override
    protected void zoomMap(final int amount) {
        final OrthographicCamera camera = (OrthographicCamera) getCamera();
        final float viewportWidth = camera.viewportWidth;
        final float viewportHeight = camera.viewportHeight;
        final float currentZoom = camera.zoom;

        //zoom in or out
        //alternatively camera.zoom = camera.zoom * (1 - 0.1f * amount);
        if (amount > 0) {
            camera.zoom = camera.zoom / 2f;
        } else {
            camera.zoom = camera.zoom * 2f;
        }

        //check if zoom is inside allowed min and max values
        if (camera.zoom < minZoom) {
            camera.zoom = minZoom;
        } else if (camera.zoom > maxZoom) {
            camera.zoom = maxZoom;
        }

        //lower zoom if rendered map would be smaller then viewport. Ie there should not be any blank (black) space in viewport
        final float maxMapZoom = Math.min(worldMap.getBiomesData().length * 32 / viewportWidth, worldMap.getBiomesData()[0].length * 32 / viewportHeight);
        final int maxMapZoomDivisorTwo = Math.max(1, ((int)maxMapZoom/2)*2);
        if (camera.zoom  > maxMapZoom) {
            camera.zoom = maxMapZoomDivisorTwo;
        }

        //move camera if outside of bounds
        if(fixBounds) {
            fixBounds();
        }
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public void addObserver(MapStageObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MapStageObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void removeAllObservers() {
        observers.clear();
    }

    @Override
    public void notify(MapStageObserver.MapStageEvent event, Object... values) {
        observers.stream().forEach(mapStageObserver -> mapStageObserver.onNotify(event, values));
    }
}
