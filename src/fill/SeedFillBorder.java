package fill;


import com.sun.source.util.SourcePositions;
import model.Point;
import model.Polygon;
import rasterize.LineRasterizer;
import rasterize.Raster;

import java.awt.*;

public class SeedFillBorder implements Filler {

    private final Raster raster;
    LineRasterizer lineRasterizer;
    Polygon polygon;
//    private final Polygon polygon;

    private int borderCollor;
    private int fillCollor;
    private int seedX, seedY;
    private int [] [] matrix;


    /**
     * Konstruktor s jedním parametrem
     * @param raster
     */

    public SeedFillBorder(Raster raster) {
        this.raster = raster;
    }

    /**
     * Metoda - nastavení barvy hranice - definovana v Controlleru
     */

    public void setBorderColor(int borderCollor) {

        this.borderCollor=borderCollor;
    }

    /**
     * Metoda - nastavení "seminka", bodu od ktereho vybarvuji. Parametry mám bod, od kterho vybarvuji a barvu hranice
     * @param seed
     * @param borderColor
     */
    public void setSeed(Point seed, int borderColor) {
        seedX = seed.x;
        seedY = seed.y;
       this.borderCollor = borderColor;
    }


    /**
     * Metoda - nastavení barvy pro vyplnění.
     * @param fillCollor
     */
    public void setFillCollor(int fillCollor) {
        this.fillCollor = fillCollor;
    }

    /**
     * Metoda - vyplneni obrazce
     */
    @Override
    public void fill() {
        seedFill(seedX, seedY);

    }

    private void seedFill(int x, int y) {


        if (x > 0 && y > 0 && x < this.raster.getWidth()  && y < this.raster.getHeight()) {
            if (borderCollor != raster.getPixel(x,y) && raster.getPixel(x,y)!=fillCollor) {

                raster.setPixel(x, y, fillCollor);
                seedFill(x + 1, y);
                seedFill(x - 1, y);
                seedFill(x, y + 1);
                seedFill(x, y - 1);
        }
    }
    }
}
