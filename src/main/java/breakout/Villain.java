package breakout;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class Villain extends Rectangle{
    private int speed;
    public Villain (double w, double h, double x, double y, int villain_speed) {
        super(x, y, w, h);
        setFill(Color.BLACK);
        setStroke(Color.BLACK);

        speed = villain_speed;
    }

    public int getSpeed() {
        return speed;
    }
}