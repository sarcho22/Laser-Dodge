import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PenelIsDed extends Application {
    public void start (Stage primaryStage) {
        Pane pane = new Pane();

//        Rectangle ground1 = new Rectangle();
//        ground1.setFill(Color.POWDERBLUE);
//        ground1.setStroke(Color.PALEGOLDENROD);
//        ground1.setStrokeWidth(1);
//        ground1.widthProperty().bind(pane.widthProperty().divide(3));
//        ground1.heightProperty().bind(pane.heightProperty().divide(10));
//        ground1.setX(0);
//        ground1.setY(720);
//        ground1.yProperty().bind(pane.heightProperty().subtract(ground1.heightProperty()));
//        pane.getChildren().add(ground1);
//

        Circle c = new Circle(15, 15, 20);
        c.setFill(Color.POWDERBLUE);
        pane.getChildren().add(c);

        Circle eye1 = new Circle();
        eye1.centerXProperty().bind(c.centerXProperty().subtract(11));
        eye1.centerYProperty().bind(c.centerYProperty().subtract(7));
        eye1.setFill(Color.BLACK);
        pane.getChildren().add(eye1);

        Circle eye2 = new Circle();
        eye2.centerXProperty().bind(c.centerXProperty().add(11));
        eye2.centerYProperty().bind(c.centerYProperty().subtract(7));
        eye2.setFill(Color.BLACK);
        pane.getChildren().add(eye2);

//        Image marioImage = new Image("mario.png");
//        ImageView mario = new ImageView(marioImage);
//        mario.setFitHeight(60);
//        mario.setFitWidth(60);
//        mario.setX(5);
//        mario.setY(670);
//        pane.getChildren().add(mario);

        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT){
                c.setCenterX(c.getCenterX() + 5);
            }
            else if (e.getCode() == KeyCode.LEFT){
                c.setCenterX(c.getCenterX() - 5);
            }
            else if (e.getCode() == KeyCode.DOWN){
                c.setCenterY(c.getCenterY() + 5);
            }
            else if (e.getCode() == KeyCode.UP){
                c.setCenterY(c.getCenterY() - 5);
            }
        });

        Scene scene = new Scene(pane, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mario YEET");
        primaryStage.show();
        pane.requestFocus();
    }
}
