package fill;

import model.Point;
import rasterize.Raster;

public class SeedFill implements Filler {

    private final Raster raster;

    private int backgroundColor;
    private int backgroundColor2;
    private int fillCollor;
    private int seedX, seedY;
    private int [] [] matrix;
    private int [] color;

    public SeedFill(Raster raster) {
        this.raster = raster;

        matrix = new int[][]{
                {1, 0},
                {1, 0}
        };
    }

    public void setSeed(Point seed) {
        seedX = seed.x;
        seedY = seed.y;
        backgroundColor = raster.getPixel(seedX, seedY);
    }

    public void setFillCollor(int fillCollor) {
        this.fillCollor = fillCollor;
    }

//    public void setMatrixFillCollor(int matrixFillCollor)


    @Override
    public void fill() {
        seedFill(seedX, seedY);
    }

//    public void fillMatrix(int x, int y, int...color) {
//        raster.getPixel(x, y);
//        this.color = color.clone();
//        fill(x,y);
//    }

    private void seedFill(int x, int y) {
        if (x > 0 && y > 0 && x < this.raster.getWidth() && y < this.raster.getHeight()) {
            if (backgroundColor == raster.getPixel(x, y)) {
                raster.setPixel(x, y, fillCollor);
                seedFill(x + 1, y);
                seedFill(x - 1, y);
                seedFill(x, y + 1);
                seedFill(x, y - 1);
            }
        }
    }

    private void seedFillMatrix (int x, int y) {
        if (x > 0 && y > 0 && x < this.raster.getWidth() && y < this.raster.getHeight()) {
            if (backgroundColor == raster.getPixel(x, y)) {
                int index = matrix[x / 2 % 10][x / 2 / 2 % 38];
                raster.setPixel(x, y, color [index]);
                seedFillMatrix(x + 1, y);
                seedFillMatrix(x - 1, y);
                seedFillMatrix(x, y + 1);
                seedFillMatrix(x, y - 1);
            }
        }
    }
}
