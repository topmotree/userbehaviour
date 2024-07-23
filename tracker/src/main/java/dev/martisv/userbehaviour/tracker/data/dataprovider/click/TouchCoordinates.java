package dev.martisv.userbehaviour.tracker.data.dataprovider.click;

public class TouchCoordinates {
    private int x;
    private int y;

    public TouchCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "TouchCoordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
