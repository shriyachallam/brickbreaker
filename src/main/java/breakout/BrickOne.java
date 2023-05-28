package breakout;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class BrickOne extends Brick{
    public BrickOne (double w, double h, double x, double y) {
        super(w, h, 1, x, y);
        setFill(Color.YELLOW);
    }
}
