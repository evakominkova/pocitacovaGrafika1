package rasterize;


public class DottedLineRasterize extends LineRasterizer {


    public DottedLineRasterize(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        float k = (float) (y2 - y1) / (float) (x2 - x1);
        float q = (y1 - (k * x1));

        int a = 0;
        int mezera = 10;


        if (Math.abs(k) < 1) {

            if (x2 < x1) {
                int prohozx = x1;
                x1 = x2;
                x2 = prohozx;

                int prohozy = y1;
                y1 = y2;
                y2 = prohozy;
            }

            for (a = x1; a <= x2; a++) {
                float kx = (float) (y2 - y1) / (float) (x2 - x1);
                float qx = (float) y1 - (float) x1 * kx;
                float y = (kx * a) + qx;

                if (a % mezera == 0) {
                    raster.setPixel(a, Math.round(y), color);
                }

            }
        } else {
            if (y2 < y1) {
                int prohozx = x1;
                x1 = x2;
                x2 = prohozx;

                int prohozy = y1;
                y1 = y2;
                y2 = prohozy;
            }

            float ky = (float) (x2 - x1) / (float) (y2 - y1);
            float qy = (float) x1 - (float) y1 * ky;

            for (a = y1; a <= y2; a++) {
                float y = (a * ky) + qy;


                if (a % mezera == 0) {
                    raster.setPixel(Math.round(y), a, color);
                }
            }
        }
    }
}
