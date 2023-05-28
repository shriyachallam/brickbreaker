package breakout;

import javafx.scene.paint.Color;

public class BrickFour extends Brick{
    public BrickFour (double w, double h, double x, double y) {
        super(w, h, 4, x, y);
        setFill(Color.DARKRED);
    }
}
