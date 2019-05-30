import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PenelIsDed {
    public void start (Stage primaryStage) {
        Pane pane = new Pane();
        Image marioImage = new Image("mario.png");
        ImageView mario = new ImageView(marioImage);
        mario.setFitHeight(58);
        mario.setFitWidth(72);
        pane.getChildren().add(mario);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mario YEET");
        primaryStage.show();
    }
}
