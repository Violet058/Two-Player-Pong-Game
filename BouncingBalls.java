/*
main animation program that draws the bouncing balls in an infinite loop
 */
import java.util.ArrayList;
import java.awt.Color;

public class BouncingBalls {
    public static void main(String[] args) {
        // set the scale of the coordinate system
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering(); //makes animation more efficient

        //TODO: create array of 5 new Ball objects

                ArrayList<Ball>balls=new ArrayList<Ball>();
                balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());
        balls.add(new Ball());


        // main animation loop
        while (true) { //infinite loop!  will repeat as long as we keep the program running
            // clear the background
            StdDraw.clear();


            //TODO: complete the animation drawing loop according to the instructions
            //repeat same drawing updates for every Ball in the ArrayList
            for(int i = 0; i<balls.size(); i++) {
                double cX = balls.get(i).getCenterX();
                double vX = balls.get(i).getVelocityX();
                double cY = balls.get(i).getCenterY();
                double vY = balls.get(i).getVelocityY();
                double r = balls.get(i).getRadius();

                if (Math.abs(cX + vX) > (1.0 - r)) {
                    vX = -vX;
                    balls.get(i).setVelocityX(vX);
                }
                if (Math.abs(cY + vY) > (1.0 - r)) {
                    vY = -vY;
                    balls.get(i).setVelocityY(vY);
                }

                cX = cX + vX;
                balls.get(i).setCenterX(cX);
                cY = cY + vY;
                balls.get(i).setCenterY(cY);

               //StdDraw.clear();

                StdDraw.setPenColor(balls.get(i).getBallColor());
                StdDraw.filledCircle(cX, cY, r);
            }
                // copy offscreen buffer to on screen to draw all Balls' updated positions
                StdDraw.show();
                // pause for 20 ms
                StdDraw.pause(20);
            }//end animation loop
        }
    }


