package breakout;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class BrickThree extends Brick{
    public BrickThree (double w, double h, double x, double y) {
        super(w, h, 3, x, y);
        setFill(Color.RED);    }
}
