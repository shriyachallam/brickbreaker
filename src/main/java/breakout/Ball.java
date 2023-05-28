package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    private Point2D myVelocity;
    public Ball (double x, double y, double radius, Point2D velocity) {
        super(x, y, radius);
        setFill(Color.GREEN);
        setStroke(Color.BLACK);

        myVelocity = velocity;
    }

    public Point2D getVelocity() {
        return myVelocity;
    }

    public void hitSideWall() {
        myVelocity = new Point2D(-myVelocity.getX(), myVelocity.getY());
    }

    public void hitUpperWall() {
        myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
    }

    public void hitPaddleOrBrick() {
        myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
    }
}
