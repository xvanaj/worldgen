package com.vana.editor;


import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.vana.editor.component.EditorTilesWindow;
import com.vana.editor.component.WorldGenWindow;
import com.vana.view.MapStageObserver;
import com.vana.view.component.BlockingVisWindow;
import com.vana.worldgen.output.WorldMap;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;

public class EditorHud extends ScreenAdapter implements MapStageObserver, EditorObservable {

    //common graphics
    private final Stage stage;
    private final Viewport viewport;
    private final Camera camera;

    //game data related
    private final WorldMap worldMap;
    private Coord tileCoord;

    private EditorTilesWindow tilesWindow;
    private VisLabel tileCoordsLabel;
    private VisLabel tileInfoLabel;
    private BlockingVisWindow tileInfoWindow;
    private VisLabel stageCoordsLabel;
    private BlockingVisWindow coordsWindow;
    private BlockingVisWindow controlsWindow;
    private BlockingVisWindow worldgenWindow;

    private List<EditorObserver> observers;

    public EditorHud(WorldMap worldMap) {
        this.observers = new ArrayList<>();
        this.stage = new Stage(new ScreenViewport()) {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                super.touchDown(screenX, screenY, pointer, button);
                return true;
            }
        };
        this.worldMap = worldMap;
        this.viewport = stage.getViewport();
        this.camera = stage.getCamera();
        ((OrthographicCamera)this.camera).zoom = 1f;

        init();
    }

    private void init() {
        controlsWindow = new BlockingVisWindow("Controls");
        VisTextButton tilesButton = new VisTextButton("T");
        tilesButton.setSize(64,64);
        controlsWindow.add(tilesButton);
        tilesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (tilesWindow == null) {
                    tilesWindow = new EditorTilesWindow(EditorHud.this, "Tiles");
                    stage.addActor(tilesWindow);
                } else {
                    tilesWindow.setVisible(!tilesWindow.isVisible());
                }
            }
        });

        VisTextButton worldgenButton = new VisTextButton("W");
        worldgenButton.setSize(64,64);
        controlsWindow.add(worldgenButton);
        worldgenButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (worldgenWindow == null) {
                    worldgenWindow = new WorldGenWindow(EditorHud.this, "Worldgen");
                    stage.addActor(worldgenWindow);
                } else {
                    worldgenWindow.setVisible(!worldgenWindow.isVisible());
                }
            }
        });

        VisTextButton coordsButton = new VisTextButton("XY");
        coordsButton.setSize(64,64);
        controlsWindow.add(coordsButton);
        coordsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (coordsWindow == null) {
                    coordsWindow = new BlockingVisWindow("Coords");
                    stageCoordsLabel = new VisLabel("Stage: " + 64 +"/" + 128);
                    coordsWindow.add(stageCoordsLabel);
                    tileCoordsLabel = new VisLabel("Tile: " + 64 +"/" + 128);
                    coordsWindow.add(tileCoordsLabel);
                    coordsWindow.setMovable(false);
                    coordsWindow.pack();
                    coordsWindow.setWidth(100);
                    stage.addActor(coordsWindow);
                } else {
                    coordsWindow.setVisible(!coordsWindow.isVisible());
                }
            }
        });

        VisTextButton tileInfoButton = new VisTextButton("I");
        tileInfoButton.setSize(64,64);
        controlsWindow.add(tileInfoButton);
        tileInfoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (tileInfoWindow == null) {
                    tileInfoWindow = new BlockingVisWindow("Tile");
                    tileInfoWindow.setWidth(100);
                    tileInfoLabel = new VisLabel("");
                    tileInfoWindow.add(tileInfoLabel);
                    stage.addActor(tileInfoWindow);
                } else {
                    tileInfoWindow.setVisible(!tileInfoWindow.isVisible());
                }
            }
        });
        stage.addActor(controlsWindow);

        tilesWindow = new EditorTilesWindow(this,"Tiles");
        tilesWindow.setVisible(false);
        stage.addActor(tilesWindow);

        coordsWindow = new BlockingVisWindow("Coords");
        stageCoordsLabel = new VisLabel("Stage: " + 64 +"/" + 128);
        coordsWindow.add(stageCoordsLabel);
        tileCoordsLabel = new VisLabel("Tile: " + 64 +"/" + 128);
        coordsWindow.add(tileCoordsLabel);
        coordsWindow.setMovable(false);
        coordsWindow.pack();
        stage.addActor(coordsWindow);
        
        tileInfoWindow = new BlockingVisWindow("Tile");
        tileInfoWindow.setWidth(150);
        tileInfoLabel = new VisLabel("");
        tileInfoWindow.add(tileInfoLabel);
        stage.addActor(tileInfoWindow);

        resize((int)stage.getViewport().getWorldWidth(), (int)stage.getViewport().getWorldHeight());
    }

    public EditorTilesWindow getTilesWindow() {
        return tilesWindow;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, false);
        stage.getViewport().setScreenBounds(0, 0, width, height);
        stage.getViewport().apply();

        //controlsWindow.pack();
        controlsWindow.setPosition(0, height - controlsWindow.getHeight() - 50);

        if (worldgenWindow != null) {
            worldgenWindow.pack();
            worldgenWindow.setPosition(0, 0);
        }

        if (tilesWindow != null) {
            tilesWindow.pack();
            tilesWindow.setPosition(0, 0);
        }

        if (coordsWindow != null) {
            coordsWindow.pack();
            coordsWindow.setPosition(width - coordsWindow.getWidth()/*tileCoordsLabel.getGlyphLayout().width*/, height - 15 - coordsWindow.getHeight());
        }

        if (tileInfoWindow != null) {
            tileInfoWindow.setPosition(width - tileInfoWindow.getWidth(), coordsWindow.getY() - tileInfoWindow.getHeight());
        }
    }

    @Override
    public void onNotify(MapStageObserver.MapStageEvent event, Object... eventArgs) {
        if (event.equals(MapStageObserver.MapStageEvent.MOUSE_MOVED)) {
            int x = ((Float)eventArgs[0]).intValue();
            int y = ((Float)eventArgs[1]).intValue();

            tileCoord = Coord.get(x/32, y/32);

            if (stageCoordsLabel != null) {
                stageCoordsLabel.setText("Stage: " + x + "/" + y);
            }
            if (tileCoordsLabel != null) {
                tileCoordsLabel.setText("Tile: " + tileCoord.x + "/" + tileCoord.y);
            }

            if (tileInfoLabel != null && tileCoord.x > 0 && tileCoord.y > 0
                    && tileCoord.x < worldMap.getBiomesData().length && tileCoord.y < worldMap.getBiomesData()[0].length) {
                tileInfoLabel.setText(worldMap.getBiomeNameTable()[worldMap.getBiomesData()[tileCoord.x][tileCoord.y]]
                        + " (" + worldMap.getBitmaskData()[tileCoord.x][tileCoord.y] + ")");
            }
        }
    }

    @Override
    public void addObserver(EditorObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(EditorObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void removeAllObservers() {
        observers.clear();
    }

    @Override
    public void notify(EditorObserver.EditorEvent event, String... values) {
        observers.stream().forEach(observer -> observer.onNotify(event, values));
    }
}
