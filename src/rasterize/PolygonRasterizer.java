package rasterize;

import control.Controller2D;
import model.Point;
import model.Polygon;

import java.awt.*;
import java.util.List;


public class PolygonRasterizer {


    public void rasterize(Polygon polygon, LineRasterizer lineRasterizer) {


        if (polygon.getPoints().size() >= 2) {

            for (int i = 0; i < polygon.getPoints().size()-1; i++) {
                 int x1 = polygon.getPoints().get(i).x;
                int y1 = polygon.getPoints().get(i).y;

                int x2 = polygon.getPoints().get(i+1).x;
                int y2 = polygon.getPoints().get(i+1).y;
                int color =  polygon.getColor();
                lineRasterizer.rasterize(x1, y1, x2, y2, color);
            }

            int size = polygon.getPoints().size()-1;
            System.out.println("velikost seznamu je" + size);

            int x1 = polygon.getPoints().get(size).x;
            int y1 = polygon.getPoints().get(size).y;

            int x2 = polygon.getPoints().get(0).x;
            int y2 = polygon.getPoints().get(0).y;
            int color = polygon.getColor();

            lineRasterizer.rasterize(x1,y1,x2,y2,color);

        }

    }
}
