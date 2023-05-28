package breakout;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LevelFinish extends Level{
    public LevelFinish(String message) {
        super(message, 0);

        Text t = new Text (75, 200, message);
        t.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        getMyLevel().getChildren().add(t);
    }

    // override step function to not do anything during the step function
    public void step() {
        return;
    }
}
