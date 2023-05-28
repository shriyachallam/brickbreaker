package breakout;

import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.awt.*;
import javafx.scene.text.Text;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class LevelThree extends Level{
    public LevelThree() {
        super(3);

        // add villain
        Group myLevel = getMyLevel();
        Villain villain = new Villain(40, 20, 200, 100, 100);
        myLevel.getChildren().add(villain);

        makeBricks("src/main/resources/lv02.txt");
    }
}
