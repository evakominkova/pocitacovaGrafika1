package rasterize;

public class TrivialLineRasterize extends LineRasterizer {

    public TrivialLineRasterize(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {

        float k = (float) (y2 - y1) / (float) (x2 - x1);
        float q = (y1 - (k * x1));
        System.out.println(k);

        if (Math.abs(k) < 1) {

            if (x2 < x1) {
                int prohozx = x1;
                x1 = x2;
                x2 = prohozx;

                int prohozy = y1;
                y1 = y2;
                y2 = prohozy;
            }

            for (int x = x1; x <= x2; x++) {
                float kx = (float)(y2-y1)/(float) (x2-x1);
                float qx = (float)y1 - (float) x1 * kx;
                float y = (kx * x) + qx;
                raster.setPixel(x, Math.round(y), color);
            }

        } else

            {
                if (y2 < y1) {
                    int prohozx = x1;
                    x1 = x2;
                    x2 = prohozx;

                    int prohozy= y1;
                    y1 = y2;
                    y2 = prohozy;
                }

                float ky = (float) (x2 - x1)/ (float) (y2 - y1);
                float qy = (float) x1 - (float) y1 * ky;

                    for (int x = y1; x <= y2; x++) {
                        float y = (x * ky) +qy;
                        raster.setPixel(Math.round(y),x, color);
                }
            }
        }
    }
