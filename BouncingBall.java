import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;

/******************************************************************************
 *  Compilation:  javac BouncingBall.java
 *  Execution:    java BouncingBall
 *  Dependencies: StdDraw.java
 *
 *  Implementation of a 2-d bouncing ball in the box from (-1, -1) to (1, 1).
 *
 *  % java BouncingBall
 *
 ******************************************************************************/

public class BouncingBall {
    private double vx, vy;
    public static double paddle1Y;
    public static double paddle2Y;

    private Color ballColor;
    private Color paddle1Color;
    private Color paddle2Color;



    public static final double paddleWidth = .04;
    public static final double paddleHeight = paddleWidth * 5;
    public static final double pX1 = -2.95;
    public static final double pX2 = 2.95;
    public static void main(String[] args) {
        StdDraw.setCanvasSize(1050, 350);
        // set the scale of the coordinate system
        StdDraw.setXscale(-3.0, 3.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();
        pong();
    }
    public static void pong() {
        Scanner scan = new Scanner(System.in);
        // initial values for ball
        double rx = 0.500, ry = 0.500;     // position
        double vx = 0.04, vy = 0.04;     // velocity
        double radius = 0.05;
        Color ballColor = StdDraw.BLACK;
        //paddle
        double pY1 = 0, pY2 = 0;
        double movOfP1 = 0.09, moveOfP2 = 0.09;
        Color paddle1Color = StdDraw.BOOK_RED;
        Color paddle2Color = StdDraw.BOOK_LIGHT_BLUE;
        int score1 = 0;
        int score2 = 0;

        // main animation loop
        while (true) {

            // bounce off wall according to law of elastic collision

            //movement of paddle
            if (StdDraw.isKeyPressed('w') || StdDraw.isKeyPressed('W')) {
                pY1 = pY1 + movOfP1;
            }

            if (StdDraw.isKeyPressed('s') || StdDraw.isKeyPressed('S')) {
                pY1 = pY1 - movOfP1;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                pY2 = pY2 + moveOfP2;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                pY2 = pY2 - moveOfP2;
            }
            paddle1Y = pY1;
            paddle2Y = pY2;

            // ball touching the paddle OR NOT

                if (ballHitsPaddle1(rx, ry, vx, vy, pX1, paddle1Y)) {
                    vx = -vx;
                } else if (ballHitsPaddle2(rx, ry, vx, vy, pX2, paddle2Y)) {
                    vx = -vx;
                } else if (score1 == 10 || score2 == 10){
                    if(score1==10){
                        System.out.println("Player 1 won!");
                    }
                    if(score2==10){
                        System.out.println("Player 2 won!");
                    }
                    boolean answer = desireToContinuePlay(scan);
                    score1=0;
                    score2=0;
                    pY1 = 0;
                    pY2 = 0;
                } else if ((rx + vx) >= 3.0) {
                    score1++;
                    StdDraw.text(0, 0, (score1 + " : " + score2));
                    StdDraw.show();
                    StdDraw.pause(1800);
                    rx = 0.480;
                    ry = 0.860;
                    pY1 = 0;
                    pY2 = 0;
                    vx = -vx;
                } else if ((rx + vx) <= -3.0) {
                    score2++;
                    StdDraw.text(0, 0, score1 + " : " + score2);
                    StdDraw.show();
                    StdDraw.pause(1800);
                    rx = 0.480;
                    ry = 0.860;
                    pY1 = 0;
                    pY2 = 0;
                    vx = -vx;
                }


                // if (Math.abs(rx + vx) > 3.0) vx = -vx; //!!!
                if (Math.abs(ry + vy) > 1.0) vy = -vy;
                // update position of the ball.
                rx = rx + vx;
                ry = ry + vy;
                // update position of paddle

                paddle1Y = Math.min(1.0 - paddleWidth, Math.max(-1 + paddleWidth, paddle1Y));
            paddle2Y = Math.min(1.0 - paddleHeight, Math.max(-1 + paddleHeight, paddle2Y));

                // clear the background
                clear();
                //StdDraw.clear(StdDraw.LIGHT_GRAY);
                StdDraw.clear(StdDraw.WHITE);
                renderBall(rx, ry, radius, ballColor);
                renderPaddle1(paddle1Y, paddleWidth, paddleHeight, paddle1Color);
                renderPaddle2(paddle2Y, paddleWidth, paddleHeight, paddle2Color);
                //StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledCircle(rx, ry, radius);

                // copy offscreen buffer to onscreen
                StdDraw.show();

                // pause for 20 ms
                StdDraw.pause(20);



        }
    }

    public static boolean ballHitsPaddle1(double rx, double ry, double vx, double vy, double px, double py) {

        return (rx + vx > px - paddleWidth) && (rx + vx < px + paddleWidth) && (ry + vy > py - paddleHeight) && (ry + vy < py + paddleHeight);
    }
    public static boolean ballHitsPaddle2(double rx, double ry, double vx, double vy, double px, double py) {

        return (rx + vx > px - paddleWidth) && (rx + vx < px + paddleWidth) && (ry + vy > py - paddleHeight) && (ry + vy < py + paddleHeight);
    }
    public static void renderBall(double x, double y, double radius, java.awt.Color color) {

        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x, y, radius);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(x, y, radius);
    }

    public static void renderPaddle1(double y, double width, double height, java.awt.Color color) {

        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(pX1, y, width, height);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(pX1, y, width, height);
    }
    public static void renderPaddle2(double y, double width, double height, java.awt.Color color) {

        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(pX2, y, width, height);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(pX2, y, width, height);
    }

    public static void clear() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.square(0, 0, 1);
    }
    public static boolean desireToContinuePlay(Scanner s) {
        System.out.println("Do you want to start a new game? If yes, press 'y', if not - press anything else: ");
        char answer = s.next().charAt(0);
        s.nextLine();
        if (answer == 'y') {
            StdDraw.pause(600);
            return true;
            //how can it stop asking for output
        } else {
            System.out.println("Thank you for playing!");
            System.exit(0);
            return false;
        }
    }
}

