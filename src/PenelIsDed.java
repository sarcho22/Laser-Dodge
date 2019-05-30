import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PenelIsDed extends Application {
    public void start (Stage primaryStage) {
        Pane pane = new Pane();

        Rectangle ground1 = new Rectangle();
//        ground1.setHeight(pane.getHeight() / 5);
//        ground1.setWidth(pane.getWidth() / 3);
        ground1.setFill(Color.POWDERBLUE);
        ground1.setStroke(Color.PALEGOLDENROD);
        ground1.setStrokeWidth(1);
        ground1.widthProperty().bind(pane.widthProperty().divide(3));
        ground1.heightProperty().bind(pane.heightProperty().divide(10));
        ground1.setX(0);
        ground1.setY(720);
        ground1.yProperty().bind(pane.heightProperty().subtract(ground1.heightProperty()));
        pane.getChildren().add(ground1);
// hi
        Image marioImage = new Image("mario.png");
        ImageView mario = new ImageView(marioImage);
        mario.setFitHeight(60);
        mario.setFitWidth(60);
        mario.setX(5);
        mario.setY(ground1.getY() - 60);
        System.out.println(ground1.getY());
        pane.getChildren().add(mario);

        Scene scene = new Scene(pane, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mario YEET");
        primaryStage.show();
    }
}
