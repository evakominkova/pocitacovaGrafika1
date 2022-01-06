package control;

import fill.ScanLine;
import fill.SeedFill;
import fill.SeedFillBorder;
import model.Line;
import model.Point;
import model.Polygon;
//import model.Triangle;
import model.Triangle;
import rasterize.*;

import javax.swing.*;
import view.Panel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Controller2D implements Controller {

    private final Panel panel;
    private final Raster raster;

    private LineRasterizer trivialLineRasterizer;
    private DDALineRasterize ddaLineRasterize;
    private Polygon polygon;
    private PolygonRasterizer polygonRasterize;
    private DottedLineRasterize dottedLineRasterize;
    private SeedFill seedFill;
    private BresenhamRasterize bresenhamRasterize;
    private ScanLine scanLine;
    private Triangle triangle;
    private CircleRasterize circleRasterize;

    public boolean triangleMode = false; //pro spusteni vykresleni trojuhelniku .. nutno, aby bylo true




    private int x,y;
    private LineRasterizerGraphics rasterizer;
    private SeedFillBorder seedFillBorder;
    List<Point> points = new ArrayList<>();

    public Controller2D(Panel panel) {
        this.panel = panel;
        this.raster= panel.getRaster();
        initObjects(panel.getRaster());
        initListeners(panel);
    }


    public void initObjects(Raster raster) {

        trivialLineRasterizer = new TrivialLineRasterize(raster);
        dottedLineRasterize = new DottedLineRasterize(raster);
        polygonRasterize = new PolygonRasterizer();
        seedFill = new SeedFill(raster);
        seedFillBorder = new SeedFillBorder(raster);
        ddaLineRasterize = new DDALineRasterize(raster);
        bresenhamRasterize = new BresenhamRasterize(raster);
        scanLine = new ScanLine(raster,points,0xf00f01,0xf00ff1);
        polygon = new Polygon();
        circleRasterize = new CircleRasterize(raster);


        Point a = new Point(x,y);
        Point b = new Point(ThreadLocalRandom.current().nextInt(200, 500), ThreadLocalRandom.current().nextInt(100, 400));

triangle = new Triangle(a, b);
//        polygon.addPoints(new Point(10,10), new Point(50,50));




     }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    x = e.getX();
                    y = e.getY();

                } else if (SwingUtilities.isLeftMouseButton(e)) {
//                    rasterizer.drawLine(x,y,e.getX(),e.getY());
                    x = e.getX();
                    y = e.getY();

                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    raster.clear();
                    points.clear();
                    polygon.addPoints(new Point(e.getX(), e.getY()));

                } else if (SwingUtilities.isRightMouseButton(e)) {
                    x = e.getX();
                    y = e.getY();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        seedFillBorder.setSeed(new Point(e.getX(), e.getY()), polygon.getColor());
//                       seedFillBorder.setBorderColor(polygon.getColor());
                        seedFillBorder.setFillCollor(Color.RED.getRGB());
                        seedFillBorder.fill();

                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        seedFill.setSeed(new Point(e.getX(), e.getY()));
                        seedFill.setFillCollor(Color.BLUE.getRGB());
                        seedFill.fill();
                    }
                }
                if (e.isShiftDown()) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        System.out.println("System pripraven pro scanline");
                       scanLine.fill();
                }
                    if (SwingUtilities.isRightMouseButton(e)) {
                        System.out.println("Vykresleni kruznice");
                        raster.clear();
                        points.clear();
                        circleRasterize.rasterize(x, y, e.getX(), e.getY(),0xffffff );
        }};

                panel.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if (e.isShiftDown()) {
//                            if (SwingUtilities.isMiddleMouseButton(e)) {
//                                raster.clear();
//                                System.out.println("system pripraven pro Bresenham");
//                                bresenhamRasterize.rasterize(x, y, e.getX(), e.getY(), 0xfffff1);
//                            }

                        } else if (SwingUtilities.isLeftMouseButton(e)) {
                            raster.clear();
                            trivialLineRasterizer.rasterize(x, y, e.getX(), e.getY(), 0xffffff);


//                    raster.clear();
//                    ddaLineRasterize.rasterize(x,y,e.getX(), e.getY(), 0xffffff);


                        } else if (SwingUtilities.isRightMouseButton(e)) {
                            raster.clear();
                            dottedLineRasterize.rasterize(x, y, e.getX(), e.getY(), 0xfffff1);

                        } else if (SwingUtilities.isMiddleMouseButton(e)) {
//                    raster.clear();
                            polygonRasterize.rasterize(polygon, trivialLineRasterizer);

                        }
                    }
                });

                panel.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        // na klávesu C vymazat plátno
                        if (e.getKeyCode() == KeyEvent.VK_C) {
                            raster.clear();
                            polygon.getPoints().clear(); //mazu seznam bodu polygonu
                        }

                        if (e.getKeyCode() == KeyEvent.VK_T) {
                            triangleMode = true;
                            System.out.println("kresli trojuhelnik");
                        }

                    }
                });

                panel.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        panel.resize();
                        initObjects(panel.getRaster());
                    }
                });
            }


//            private String showColorDialog() {
//                return JOptionPane.showInputDialog("zadej barvu");
//            }




            private void update() {
//        panel.clear();
                //TODO

            }

            private void hardClear() {
                panel.clear();
            }
        });
    }
}
