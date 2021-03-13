package view.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ScrollingStage extends Stage {

    protected float minZoom = 1f/4f;
    protected int maxZoom = Integer.MAX_VALUE;

    public ScrollingStage(ScreenViewport screenViewport) {
        super(screenViewport);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        boolean processed = super.touchDragged(screenX, screenY, pointer);

        float x = Gdx.input.getDeltaX();
        float y = Gdx.input.getDeltaY();

        final Vector3 unproject = ((OrthographicCamera)getCamera()).unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        ((OrthographicCamera)getCamera()).translate(-x * ((OrthographicCamera)getCamera()).zoom, y * ((OrthographicCamera)getCamera()).zoom);
                    /*if (camera.position.x < - worldMap.mapGeneratorInput.WIDTH * Settings.TILE_WIDTH / 4) {
                        camera.position.x = (- worldMap.mapGeneratorInput.WIDTH * Settings.TILE_WIDTH / 4);
                    }*/

        return processed;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        Vector2 v = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        zoomMap((int) amountY);

        return true;
    }

    protected void zoomMap(final int amount) {
        final OrthographicCamera camera = (OrthographicCamera) getCamera();
        final float viewportWidth = camera.viewportWidth;
        final float viewportHeight = camera.viewportHeight;

        //camera.zoom = camera.zoom * (1 - 0.1f * amount);
        if (amount > 0) {
            ((OrthographicCamera)getCamera()).zoom = ((OrthographicCamera)getCamera()).zoom / 2f;
        } else {
            ((OrthographicCamera)getCamera()).zoom = ((OrthographicCamera)getCamera()).zoom * 2f;
        }

        //check if zoom is inside allowed min and max values
        if (((OrthographicCamera)getCamera()).zoom < minZoom) {
            ((OrthographicCamera)getCamera()).zoom = minZoom;
        } else if (((OrthographicCamera)getCamera()).zoom > maxZoom) {
            ((OrthographicCamera)getCamera()).zoom = maxZoom;
        }

/*        if (camera.viewportWidth * camera.zoom * (1 - 0.05f * amount) < bigWidth) {
            camera.zoom = camera.zoom * (1 - 0.05f * amount);
        } else {
            camera.zoom = bigWidth / camera.viewportWidth;
        }*/

    }
}
