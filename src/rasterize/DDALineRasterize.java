package rasterize;

public class DDALineRasterize extends LineRasterizer {

    public DDALineRasterize (Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize (int x1, int y1, int x2, int y2, int color) {

        int dy = y2 - y1;
        int dx = x2 - x1;
        float m;
        float h;
        float k = (y2 - y1) / (x2 - x1);
        float g;
        float x = x1;
        float y = y1;

        if (Math.abs(dx) > Math.abs(dy)) {
            g = 1;
            h = k;
        } else {
            g = 1 / k;
            h = 1;
        }

        for (int i = 0; i <= Math.max(dx, dy); i++) {
            raster.setPixel(Math.round(x), Math.round(y), color);
            x = x + g;
            y = y + h;
        }
    }
}
