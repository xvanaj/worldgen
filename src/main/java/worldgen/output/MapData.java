package worldgen.output;

import java.util.Arrays;

public class MapData {

    private double[][] data;
    public double min;
    public double max;
    public double avg;
    //todo add something like standard deviance from avg...

    public MapData(int width, int height) {
        data = new double[width][height];

        min = Float.MAX_VALUE;
        max = Float.MIN_VALUE;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "MapData{" +
                "data=" + Arrays.toString(data) +
                ", min=" + min +
                ", max=" + max +
                ", avg=" + avg +
                '}';
    }
}
