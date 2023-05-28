package breakout;

import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.awt.*;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class LevelOne extends Level{
    public LevelOne() {
        super(1);

        Group myLevel = getMyLevel();

        makeBricks("src/main/resources/lv01.txt");
    }
}
