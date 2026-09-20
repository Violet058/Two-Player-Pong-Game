/*
definition for a single Ball object
a Ball can be created on the drawing window and will move and "bounce"
each ball has a position (indicated by (x,y) coordinates of its center, a velocity (in x and y components),
a radius, and a color
 */

import java.awt.Color;
import java.util.Random;

import static java.awt.Color.*;

public class Ball {
    //private instance variables that describe attributes of this Ball object
    private double centerX, centerY; //coordinates of the center of this Ball
    private double velocityX, velocityY; //x & y components of the velocity of this Ball
    private double radius;
    private Color ballColor;





    Color[] randColor = {StdDraw.CYAN, StdDraw.PINK, StdDraw.BOOK_BLUE, StdDraw.BOOK_LIGHT_BLUE, StdDraw.BOOK_RED};

    //TODO: create default constructor according to assignment instructions
    public Ball() {
        Random rand = new Random();
        centerX = 0;
        centerY = 0;
        velocityX = ((rand.nextInt(20)+10)*.001);
        velocityY = ((rand.nextInt(20)+10)*.001);
        radius = ((rand.nextInt(5)+5)*.01);

        ballColor = randColor[rand.nextInt(5)];

    }
    //TODO: create "getter" and "setter" methods according to assignment instructions


    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    public double getVelocityY() {
        return velocityY;
    }
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getRadius() {
        return radius;
    }

    public Color getBallColor() {
        return ballColor;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getCenterX() {
        return centerX;
    }
}