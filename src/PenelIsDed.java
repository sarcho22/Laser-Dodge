import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PenelIsDed extends Application {
    public void start (Stage primaryStage) {
        Pane pane = new Pane();

        Rectangle ground1 = new Rectangle();
        ground1.setFill(Color.POWDERBLUE);
        ground1.setStroke(Color.PALEGOLDENROD);
        ground1.setStrokeWidth(1);
        ground1.widthProperty().bind(pane.widthProperty().divide(3));
        ground1.heightProperty().bind(pane.heightProperty().divide(10));
        ground1.setX(0);
        ground1.setY(720);
        ground1.yProperty().bind(pane.heightProperty().subtract(ground1.heightProperty()));
        pane.getChildren().add(ground1);

        Rectangle ground2 = new Rectangle();
        ground2.setFill(Color.POWDERBLUE);
        ground2.setStroke(Color.PALEGOLDENROD);
        ground2.setStrokeWidth(1);
        ground2.widthProperty().bind(pane.widthProperty().divide(3));
        ground2.heightProperty().bind(pane.heightProperty().divide(10));
        ground2.xProperty().bind(ground1.widthProperty().add(40));
        ground2.setY(720);
        ground2.yProperty().bind(pane.heightProperty().subtract(ground2.heightProperty()));
        pane.getChildren().add(ground2);

        Rectangle ground3 = new Rectangle();
        ground3.setFill(Color.POWDERBLUE);
        ground3.setStroke(Color.PALEGOLDENROD);
        ground3.setStrokeWidth(1);
        ground3.widthProperty().bind(pane.widthProperty().divide(3));
        ground3.heightProperty().bind(pane.heightProperty().divide(10));
        ground3.xProperty().bind(ground2.widthProperty().add(60).add(ground2.xProperty()));
        ground3.setY(720);
        ground3.yProperty().bind(pane.heightProperty().subtract(ground3.heightProperty()));
        pane.getChildren().add(ground3);

        Image marioImage = new Image("mario.png");
        ImageView mario = new ImageView(marioImage);
        mario.setFitHeight(60);
        mario.setFitWidth(60);
        mario.setX(5);
        mario.setY(670);
        pane.getChildren().add(mario);

        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT){
                mario.setX(mario.getX() + 5);
            }
            else if (e.getCode() == KeyCode.LEFT){
                mario.setX(mario.getX() - 5);
            }
            else if (e.getCode() == KeyCode.DOWN){
                mario.setY(mario.getY() + 5);
            }
            else if (e.getCode() == KeyCode.UP){
                mario.setY(mario.getY() - 5);
            }
        });

        Scene scene = new Scene(pane, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mario YEET");
        primaryStage.show();
        pane.requestFocus();
    }
}
