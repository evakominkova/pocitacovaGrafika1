package model;

import rasterize.LineRasterizer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polygon {

    private final List<Point> points;
    private final int color;

    public Polygon() {
        this(new ArrayList<>());
    }

    public Polygon(List<Point> points) {
        this(points, Color.MAGENTA.getRGB());
    }

    public Polygon(List<Point> points, int color) {
        this.points = points;
        this.color = color;
    }

    public Point getPoint (boolean first) {    //prvni bod v seznamu
        return (first)? points.get(0): points.get(points.size()-1);
    }

    public void addPoints(Point... newPoints) {
        points.addAll(Arrays.asList(newPoints));
        {
            System.out.println(newPoints);
        }
    }

    public void addPoints(List<Point> newPoints) {
        points.addAll(newPoints);
    }

    public void addPoints (int index, Point point) {
        points.add(index,point);
    }

    public List<Point> getPoints() {
        return points;
    }

    public int getColor() {
        return color;
    }

    public void rasterize(LineRasterizer lineRasterizer) {
    }

    public List<Point> getCopyOfPoints() {
        return new ArrayList<>(points);
    }

}
