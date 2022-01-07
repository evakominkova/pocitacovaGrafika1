package controller;

import control.Controller;
import model3d.Cube;
import model3d.Scene;
import rasterize.Raster;
import renderer.GPURenderer;
import renderer.Renderer3D;
import transforms.*;
import view.Panel;

public class Controller3D implements Controller {

    private Camera camera;
    private Mat4 projection;
    private Raster raster;
    private GPURenderer renderer;
    private Scene mainScene, axisScene;

    public Controller3D(Panel panel) {
        this.raster = panel.getRaster();
        this.renderer = new Renderer3D(raster);
        initListeners(panel);

        camera = new Camera().withPosition(new Vec3D(0.8,-5,2)).withAzimuth(Math.toRadians(90)).withZenith(-15);

        projection = new Mat4PerspRH(
                Math.PI /3,
                raster.getHeight() / (float) raster.getWidth(),
                0.1,
                50);


//        new Mat4OrthoRH(20,20,0.1,50);

        mainScene = new Scene();
        mainScene.getSolids().add(new Cube());
        display();
    }

    private void display() {
        raster.clear();
        renderer.setView(camera.getViewMatrix());
        renderer.setProjectionMatrix(projection);
        renderer.draw(mainScene);
    }


    @Override
    public void initListeners(Panel panel) {
        //todo
    }
}
