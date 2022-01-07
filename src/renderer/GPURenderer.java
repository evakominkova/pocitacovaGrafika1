package renderer;

import model3d.Scene;
import transforms.Mat4;

public interface GPURenderer {

    void draw(Scene scene);

    void setModel(Mat4 model); //modelovaci (kombinace scale, rotace, posunuti)

    void setView(Mat4 view); //pohledova

    void setProjectionMatrix(Mat4 projection); // projekcni

}
