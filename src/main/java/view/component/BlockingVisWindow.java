package view.component;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisWindow;

public class BlockingVisWindow extends VisWindow {

    public BlockingVisWindow(String title) {
        super(title);
        getTitleLabel().setAlignment(Align.center);
        addCloseButton();

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
    }

    /**
     * Overridden parent method because we dont want to close window but only hide it (set visible false)
     */
    @Override
    public void addCloseButton () {
        Label titleLabel = getTitleLabel();
        Table titleTable = getTitleTable();

        VisImageButton closeButton = new VisImageButton("close-window");
        titleTable.add(closeButton).padRight(-getPadRight() + 0.7f);
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                setVisible(false);
            }
        });
        closeButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                event.cancel();
                return true;
            }
        });

        if (titleLabel.getLabelAlign() == Align.center && titleTable.getChildren().size == 2)
            titleTable.getCell(titleLabel).padLeft(closeButton.getWidth() * 2);
    }
}
