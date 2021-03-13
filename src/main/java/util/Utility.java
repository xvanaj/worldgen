package util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import squidpony.squidgrid.gui.gdx.SquidColorCenter;

import java.util.*;
import java.util.stream.Collectors;

public final class Utility {

    //used to check how long does it take to load assets statically. Whole class should be refactored when
    // this will take too long
    private static final long ttg;
    static {
        ttg = System.currentTimeMillis();
    }

    public static SquidColorCenter squidColorCenter = new SquidColorCenter();
    public static TextureAtlas TEXTURE_ATLAS = new TextureAtlas("texture/lpc-terrains/lpc-terrains.atlas");
    public static TextureAtlas SIMPLE_TEXTURE_ATLAS = new TextureAtlas("texture/lpc-terrains/lpc-terrain.atlas");


    static {
        System.out.println("Assets loaded in " + (System.currentTimeMillis() - ttg));
    }
}
