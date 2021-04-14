package com.vana.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import squidpony.squidgrid.gui.gdx.SquidColorCenter;
import squidpony.squidmath.StatefulRNG;

public final class Utility {

    //used to check how long does it take to load assets statically. Whole class should be refactored when
    // this will take too long
    private static final long ttg;
    static {
        ttg = System.currentTimeMillis();
    }

    public static final StatefulRNG RNG = new StatefulRNG();
    public static SquidColorCenter squidColorCenter = new SquidColorCenter();

    public static TextureAtlas SIMPLE_TEXTURE_ATLAS = new TextureAtlas("texture/lpc-terrains/lpc-terrain.atlas");


    static {
        System.out.println("Assets loaded in " + (System.currentTimeMillis() - ttg));
    }
}
