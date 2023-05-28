package breakout;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class Paddle extends Rectangle{
    public Paddle (double w, double h, double x, double y) {
        super(x, y, w, h);
        setFill(Color.PURPLE);
        setStroke(Color.BLACK);
    }
}