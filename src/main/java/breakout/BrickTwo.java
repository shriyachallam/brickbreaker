package breakout;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class BrickTwo extends Brick{
    public BrickTwo (double w, double h, double x, double y) {
        super(w, h, 2, x, y);
        setFill(Color.ORANGE);
    }
}
