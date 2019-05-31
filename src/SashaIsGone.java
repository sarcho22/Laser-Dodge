import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SashaIsGone extends Application {
    public void start (Stage primaryStage) {
        Pane pane = new Pane();

        Rectangle board = new Rectangle();
        board.setFill(Color.BEIGE);

        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT){
                player.setX(player.getX() + 5);
            }
            else if (e.getCode() == KeyCode.LEFT){
                player.setX(player.getX() - 5);
            }
            else if (e.getCode() == KeyCode.DOWN){
                player.setY(player.getY() + 5);
            }
            else if (e.getCode() == KeyCode.UP){
                player.setY(player.getY() - 5);
            }
        });



        Scene scene = new Scene(pane, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Laser Dodgeball");
        primaryStage.show();
        pane.requestFocus();
    }
}
