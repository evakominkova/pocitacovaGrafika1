package model;

import java.awt.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// poznamka k Thalletove vete. Thaletova veta rika, ze vsechny trojuhelniky, jejichz stred kruznice opsane lezi na preponě
//tohoto trojuhelniku jsou pravouhle. Pravouhli trojuhelnik ma tri vrcholy - abc. Treti vrchol" ten nezadany" se dopocte Thal. vetou.

public class Triangle {

    private Point a;
    private Point b;
    private Point c;
    private Point stred;

    public Point getA() {
    return a;}

    public void setA(Point a) {
        this.a = a;
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }

    public Point getC() {
        return c;
    }

    public void setC(Point c) {
        this.c = c;
    }

    public Point getStred() {
        return stred;
    }

    public void setStred(Point stred) {
        this.stred = stred;
    }

    //konstruktor - parametry a a b, ktere zadavam klikem
    public Triangle (Point a, Point b) {
        this.a = a;
        this.b = b;
        setStred(new Point(((a.getX() + b.getX())) / 2, ((a.getY() + b.getY()) / 2))); //polovina prepony
        int dist = (int) (Math.sqrt(Math.pow(stred.getX() - a.getX(), 2) + Math.pow(stred.getY() - a.getY(), 2)));
        setC (new Point(stred.getX(), stred.getY() - dist)); // nastaveni bodu c
    }
}