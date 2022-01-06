package renderer;

import model3d.Scene;
import transforms.Mat4;

public interface GPURenderer {

    void draw(Scene scene);

    void setModel(Mat4 model); //modelovaci (kombinace scale, rotace, posunuti)
    void setModel1(Mat4 model); //modelovaci (kombinace scale, rotace, posunuti)

    void setView(Mat4 view); //pohledova

    void setProjection(Mat4 projection); // projekcni

    void resetMatrix();


}
