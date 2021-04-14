package com.vana.util;

public class ArrayUtils {

    public static void printArray(float[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                //System.out.println(String.format("%.2f", array[j][i])); //commented temporarily because of gwt
            }
            System.out.println();
        }
    }

    public static double[][] toDouble(final float[][] floatData) {
        int width = floatData.length;
        int height = floatData[0].length;

        final double[][] doubleData = new double[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                doubleData[x][y] = floatData[x][y];
            }
        }

        return doubleData;
    }
}
