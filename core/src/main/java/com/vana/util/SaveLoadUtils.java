package com.vana.util;

import com.badlogic.gdx.files.FileHandle;
import com.vana.worldgen.input.MapGeneratorInput;
import org.apache.log4j.Logger;

public class SaveLoadUtils {

    static Logger logger = Logger.getLogger(SaveLoadUtils.class.getName());

    public static FileHandle save(final Object o, final String filePath) {
/*        final Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        final FileHandle fileHandle = new FileHandle(filePath);
        fileHandle.writeString(gson.toJson(o).toString(), false);

        return fileHandle;*/

        logger.info("saving ...");
        return null;
    }

    public static Object load(final Class clazz, final String filePath) {
        /*final Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        final FileHandle fileHandle = new FileHandle(filePath);
        final Object o = gson.fromJson(fileHandle.readString(), clazz);

        return o;*/
        return null;
    }

    public static void main(String[] args) {
        save(new MapGeneratorInput(), "assets/save/test");
        Object load = load(MapGeneratorInput.class, "assets/save/test");
        System.out.println(load.toString());
    }
}
