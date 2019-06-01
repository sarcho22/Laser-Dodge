import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SashaIsGone extends Application {
    public void start (Stage primaryStage) {
        Pane pane = new Pane();

        Rectangle board = new Rectangle();
        board.setFill(Color.BEIGE);
        board.setX(0);
        board.setY(0);
        board.widthProperty().bind(pane.widthProperty());
        board.heightProperty().bind(pane.heightProperty());
        pane.getChildren().add(board);

        Circle p1 = new Circle(250, 250, 20);
        p1.setFill(Color.POWDERBLUE);


        int score = 0;
        boolean alive = true;

        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT){
                if(p1.getCenterX() + p1.getRadius() >= 500) {
                    p1.setCenterX(p1.getCenterX());
                }
                else {
                    p1.setCenterX(p1.getCenterX() + 5);
                }

            }
            else if (e.getCode() == KeyCode.LEFT){
                if(p1.getCenterX() - p1.getRadius() <= 0) {
                    p1.setCenterX(p1.getCenterX());
                }
                else {
                    p1.setCenterX(p1.getCenterX() - 5);
                }
            }
            else if (e.getCode() == KeyCode.DOWN){
                if(p1.getCenterY() + p1.getRadius() >= 500) {
                    p1.setCenterY(p1.getCenterY());
                }
                else {
                    p1.setCenterY(p1.getCenterY() + 5);
                }
            }
            else if (e.getCode() == KeyCode.UP){
                if(p1.getCenterY() - p1.getRadius() <= 0) {
                    p1.setCenterY(p1.getCenterY());
                }
                else {
                    p1.setCenterY(p1.getCenterY() - 5);
                }
            }
        });

        pane.getChildren().add(p1);

        /*
        while(alive) {
            for(int i = 0; i < score; i++) {
                drawLine1 laser = new drawLine1();

            }
            score++;
         if (p1 on laser){
                    alive = false;
                    gameEnd();

        }
*/

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Laser Dodgeball");
        primaryStage.show();
        pane.requestFocus();
    }
}

class drawLine1 extends Pane{
    public drawLine1() {
        Line line = new Line();
        line.setStrokeDashOffset(5);
        int direction = (int)(Math.random() * 2);
        if(direction == 0){
            line.setStartX(0);
            line.setStartY(Math.random() * 500);
            line.setEndX(500);
            line.setEndY(Math.random() * 500);
        }
        else{
            line.setStartX(Math.random() * 500);
            line.setStartY(0);
            line.setEndX(Math.random() * 500);
            line.setEndY(500);
        }
    }

}
