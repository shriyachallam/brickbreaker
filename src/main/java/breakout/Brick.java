package breakout;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class Brick extends Rectangle{
    private int hits_to_break;

    public Brick (double w, double h, int hits, double x, double y) {
        super(x, y, w, h);
        setStroke(Color.BLACK);
        hits_to_break = hits;
    }

    public boolean hit() {
        hits_to_break--;
        return hits_to_break == 0;
    }
}