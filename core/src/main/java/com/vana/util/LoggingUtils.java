package com.vana.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LoggingUtils {

    private static final String TAG_COLOR = "Amethyst";

    private static long timeTaken;                                  //in millis
    private static final Map<String, Long> timeTakenByTag = new HashMap();

    private static final Set<String> nonLoggingTags = new HashSet<>();
    static {
        nonLoggingTags.add("Movement");
    }

    /**
     * starts counting for next log event without tag
     */
    public static void startCounting() {
        timeTaken = System.currentTimeMillis();
    }

    /**
     * starts counting for log event with given tag
     * @param tag
     */
    public static void startCounting(String tag) {
        timeTakenByTag.put(tag, System.currentTimeMillis());
    }

    public static void log(final String message) {
        System.out.println(message);
    }

    public static void log(final String tag, final String message) {
        if (nonLoggingTags.contains(tag))  {
            return;
        }

        System.out.println("[" + tag + "]" + " " + message);
    }

    public static void logWithTime(final String something) {
        System.out.println(something + " took " + (System.currentTimeMillis() - timeTaken) + " millis");

        timeTaken = System.currentTimeMillis();
    }

    public static void logTimeWithTag(final String tag, final String something) {
        System.out.println(something + " took " + (System.currentTimeMillis() - timeTakenByTag.get(tag)) + " millis");

        timeTaken = System.currentTimeMillis();
    }

}
