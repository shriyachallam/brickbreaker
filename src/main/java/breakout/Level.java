package breakout;

import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.awt.*;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
    private Group myLevel;

    private int level_num;

    private int paddle_idx;

    private int ball_idx;

    private String message;

    private boolean villain_disabled;

    public Level (int num) {
        villain_disabled = false;
        level_num = num;
        myLevel = new Group();

        Point2D velocity = new Point2D(100, -100);
        Ball myBall = new Ball(250, 369, 10, velocity);
        myLevel.getChildren().add(myBall);

        ball_idx = 0;

        Paddle myPaddle = new Paddle(100, 20, 200, 380);
        myLevel.getChildren().add(myPaddle);

        paddle_idx = 1;
    }

    public Level (String my_message, int num) {
        level_num = num;
        myLevel = new Group();
        message = my_message;
    }

    public Group getMyLevel() {
        return myLevel;
    }

    public int getLevel_num() {
        return level_num;
    }

    public int step(double elapsedTime) {
        if (level_num != 0) {
            updateVillain(elapsedTime);
            return updateBall(elapsedTime);
        }
        return 0;
    }

    // this returns an int to route to the next screen depending on the int
    public int updateBall (double elapsedTime) {
        double current_x = ((Ball)(myLevel.getChildren().get(ball_idx))).getCenterX();
        double current_y = ((Ball)(myLevel.getChildren().get(ball_idx))).getCenterY();

        if (((Ball)(myLevel.getChildren().get(ball_idx))).getCenterX() >= 500 || ((Ball)(myLevel.getChildren().get(ball_idx))).getCenterX() <= 0) {
            ((Ball)(myLevel.getChildren().get(ball_idx))).hitSideWall();
        }
        else if (((Ball)(myLevel.getChildren().get(ball_idx))).getCenterY() <= 0){
            ((Ball)(myLevel.getChildren().get(ball_idx))).hitUpperWall();
        }
        else if (((Ball)(myLevel.getChildren().get(ball_idx))).getCenterY() >= 400) {
            return 1;
        }

        for (int i = 1; i < myLevel.getChildren().size(); i++) {
                if (BallHitRect(((Ball)(myLevel.getChildren().get(ball_idx))), ((Rectangle)(myLevel.getChildren().get(i))))) {
                    ((Ball)(myLevel.getChildren().get(ball_idx))).hitPaddleOrBrick();
                    // [LATE] fix a small bug (move the villain disabled boolean here)
                    // depending on whether the villain is disabled or not, and depending on which level get the right index to hit the bricks
                    if (((level_num == 1 || !villain_disabled) && i > paddle_idx) || (i > paddle_idx + 1)) {
                        if (((Brick)(myLevel.getChildren().get(i))).hit()) {
                            myLevel.getChildren().remove(i);
                            if ((level_num == 1 && myLevel.getChildren().size() == 2) || myLevel.getChildren().size() == 3) {
                                return 2;
                            }
                        }
                    }
                    break;
                }
            
        }

        Point2D velocity = ((Ball)(myLevel.getChildren().get(ball_idx))).getVelocity();

        ((Ball)(myLevel.getChildren().get(ball_idx))).setCenterX(current_x + velocity.getX() * elapsedTime);
        ((Ball)(myLevel.getChildren().get(ball_idx))).setCenterY(current_y + velocity.getY() * elapsedTime);

        return 0;
    }

    // move villain according to the ball position
    public void updateVillain (double elapsedTime) {
        if (level_num > 1 && !villain_disabled) {
            double villain_x = ((Villain)(myLevel.getChildren().get(2))).getX();
            double ball_x = ((Ball)(myLevel.getChildren().get(ball_idx))).getCenterX();

            int speed = ((Villain)(myLevel.getChildren().get(2))).getSpeed();

            if (ball_x > villain_x) {
                ((Villain)(myLevel.getChildren().get(2))).setX(villain_x + speed * elapsedTime);
            }
            else {
                ((Villain)(myLevel.getChildren().get(2))).setX(villain_x - speed * elapsedTime);
            }

        }
    }

    public boolean BallHitRect(Ball myBall, Rectangle myRect) {
        Shape shape = Shape.intersect(myBall, myRect);
        return shape.getBoundsInLocal().getWidth() != -1;
    }

    public void updatePaddle (int displacement) {
        if (level_num != 0) {
            double current_x = ((Paddle)(myLevel.getChildren().get(paddle_idx))).getX();
            double max_x = 500 - ((Paddle)(myLevel.getChildren().get(paddle_idx))).getWidth();
            if (current_x + displacement >= max_x) {
                ((Paddle)(myLevel.getChildren().get(paddle_idx))).setX(max_x);
            }
            else if (current_x + displacement <= 0) {
                ((Paddle)(myLevel.getChildren().get(paddle_idx))).setX(0);
            }
            else {
                ((Paddle)(myLevel.getChildren().get(paddle_idx))).setX(current_x + displacement);
            }
        }
    }

    public void disableVillain () {
        if (level_num > 1) {
            myLevel.getChildren().remove(2);
            villain_disabled = true;
        }
    }

    // make bricks based on text file
    public void makeBricks (String filename) {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int y = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bricks = data.split(" ");
                for (int x = 0; x < bricks.length; x++) {
                    if (!bricks[x].equals("0")) {
                        Brick brick = switch (bricks[x]) {
                            case "1" -> new BrickOne(20, 12, x * 20, y);
                            case "2" -> new BrickTwo(20, 12, x * 20, y);
                            case "3" -> new BrickThree(20, 12, x * 20, y);
                            case "4" -> new BrickFour(20, 12, x * 20, y);
                            default -> new BrickFive(20, 12, x * 20, y);
                        };
                        myLevel.getChildren().add(brick);
                    }
                }
                y += 12;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}