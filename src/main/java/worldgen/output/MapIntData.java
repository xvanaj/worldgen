package worldgen.output;

public class MapIntData {
    public int[][] data;
    public int min;
    public int max;
    public float avg;

    public MapIntData(int width, int height) {
        data = new int[width][height];
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }

    @Override
    public String toString() {
        return "MapIntData{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public float getAvg() {
        return avg;
    }
}
