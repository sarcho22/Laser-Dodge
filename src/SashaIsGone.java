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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

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



/*int score = 0;
        boolean p1alive = true;

        while(p1alive) {
//            lineline laser = new lineline();
            for(int i = 0; i < score; i++) {


                Line line = new Line();
                line.setFill(Color.WHITE);
                line.setStroke(Color.RED);
                pane.getChildren().add(line);
                Bounds lineBounds = line.getBoundsInParent();
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
                Timeline timer = new Timeline(
                        new KeyFrame(Duration.ZERO),
                        new KeyFrame(Duration.seconds(0.5))
                );
//            timer.setAutoReverse(true);
                timer.setCycleCount(5);
                if (isIntersect(p1Bounds, lineBounds)) {
                    p1alive = false;
                    break;
                }


            }
            if(p1alive) {
                score++;
                pane.getChildren().clear();
            }
            else {
                TextField ded = new TextField("YOU GO AWAY U DIED");
                break;
            }
//


        }



        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT){
                if(p1.getX() + p1.getWidth() >= 500) {
                    p1.setX(p1.getX());
                }
                else {
                    p1.setX(p1.getX() + 5);
                }

            }
            else if (e.getCode() == KeyCode.LEFT){
                if(p1.getX() <= 0) {
                    p1.setX(p1.getX());
                }
                else {
                    p1.setX(p1.getX() - 5);
                }
            }
            else if (e.getCode() == KeyCode.DOWN){
                if(p1.getY()  + p1.getHeight() >= 500) {
                    p1.setY(p1.getY());
                }
                else {
                    p1.setY(p1.getY() + 5);
                }
            }
            else if (e.getCode() == KeyCode.UP){
                if(p1.getY() <= 0) {
                    p1.setY(p1.getY());
                }
                else {
                    p1.setY(p1.getY() - 5);
                }
            }
        });
        pane.getChildren().add(p1);

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DODGE THE LASERS");
        primaryStage.show();
        pane.requestFocus();
    }
    public boolean isIntersect(Bounds p1Bounds, Bounds lineBounds) {
        if (lineBounds.intersects(p1Bounds)) {
            return true;
        }
        return false;
    }
}

//class lineline extends Pane{
//    public lineline() {
//        Line line = new Line();
//        line.setStrokeDashOffset(5);
//        int direction = (int)(Math.random() * 2);
//        if(direction == 0){
//            line.setStartX(0);
//            line.setStartY(Math.random() * 500);
//            line.setEndX(500);
//            line.setEndY(Math.random() * 500);
//        }
//        else{
//            line.setStartX(Math.random() * 500);
//            line.setStartY(0);
//            line.setEndX(Math.random() * 500);
//            line.setEndY(500);
//        }
//    }
//*/