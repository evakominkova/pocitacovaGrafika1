package rasterize;

public class BresenhamRasterize extends LineRasterizer {

    public BresenhamRasterize(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        int x = x1;
        int y = y1;
        int dx = x2 - x1;
        int dy = y2 - y1;
        int p = 2 * dy - dx;
        int k1 = 2 * dy;
        int k2 = 2 * (dy - dx);

        if (Math.abs(x2 - y1) <= Math.abs(x2 - x1)) {
            if (x2 < x1) {
                x = x1;
                x1 = x2;
                x2 = x;
                x = y1;
                y1 = y2;
                y2 = x;
            }

            dy = y2 - y1;
            dx = x2 - x1;
            x = x1;
            y = y1;
            p = 2 * Math.abs(dy) - dx;
            k1 = 2 * Math.abs(dy);
            k2 = 2 * (Math.abs(dy) - dx);
            raster.setPixel(x1, y1, color);

            for (; x < x2; raster.setPixel(x, y, color)) {
                ++x;
                if (p < 0) {
                    p += k1;
                } else {
                    p += k2;
                    if (dy > 0) {
                        ++y;
                    } else {
                        --y;
                    }
                }
            }
        } else {
            if (y2 < y1) {
                x = x1;
                x1 = x2;
                x2 = x;
                x = y1;
                y1 = y2;
                y2 = x;
            }

            dy = y2 - y1;
            dx = x2 - x1;
            x = x1;
            y = y1;
            p = 2 * Math.abs(dx) - dy;
            k1 = 2 * Math.abs(dx);
            k2 = 2 * (Math.abs(dx) - dy);
            raster.setPixel(x1, y1, color);

            for (; y < y2; raster.setPixel(x, y, color)) {
                ++y;
                if (p < 0) {
                    p += k1;
                } else {
                    p += k2;
                    if (dx > 0) {
                        ++x;
                    } else {
                        --x;
                    }
                }
            }
        }
    }
}
