package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Main extends Application {
    // useful names for constant values used
    public static final String TITLE = "Example JavaFX Animation";
    public static final Color DUKE_BLUE = new Color(0, 0.188, 0.529, 1);
    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Scene scene;

    private Level myLevel;

    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start(Stage stage) {
        try {
            myLevel = new LevelOne();

            scene = new Scene(myLevel.getMyLevel(), WIDTH, HEIGHT, DUKE_BLUE);

            if (myLevel.getLevel_num() != 0) {
                scene.setOnKeyPressed(event -> {
                    KeyCode code = event.getCode();
                    // paddle movement
                    if (code == KeyCode.LEFT) {
                        myLevel.updatePaddle(-5);
                    }
                    else if (code == KeyCode.RIGHT) {
                        myLevel.updatePaddle(5);
                    }
                    // cheat keys
                    else if (code == KeyCode.UP) {
                        nextLevel();
                    }
                    else if (code == KeyCode.DOWN) {
                        myLevel.disableVillain();
                    }
                });
            }

            stage.setScene(scene);

            stage.setTitle(TITLE);

            stage.show();

            Timeline animation = new Timeline();
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY, myLevel)));
            animation.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // switch screen to next level depending on current level
    public void nextLevel() {
        if (myLevel.getLevel_num() == 1) {
            myLevel = new LevelTwo();
        }
        else if (myLevel.getLevel_num() == 2) {
            myLevel = new LevelThree();
        }
        else {
            gameOver("Congrats, you win!");
        }
        scene.setRoot(myLevel.getMyLevel());
    }

    // switch screen to game over screen
    public void gameOver(String result) {
        myLevel = new LevelFinish(result);
    }

    // step function that calls the step function of the Level class and routes to the game over/next level screen
    public void step(double elapsedTime, Level myLevel) {
        int return_int = myLevel.step(elapsedTime);
        if (return_int == 1) {
            gameOver("Game Over!");
            scene.setRoot(this.myLevel.getMyLevel());
        }
        if (return_int == 2) {
            nextLevel();
        }
    }
}
