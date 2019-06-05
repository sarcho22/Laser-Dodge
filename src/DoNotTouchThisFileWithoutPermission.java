import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.util.Duration;

public class DoNotTouchThisFileWithoutPermission extends Application {
    public void start (Stage primaryStage) {
        Pane pane = new Pane();

        Rectangle p1 = new Rectangle(0, 0, 20, 20);
        p1.setFill(Color.POWDERBLUE);
        Bounds p1Bounds = p1.getBoundsInLocal();
        int score = 0;
        boolean p1alive = true;

        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                if (p1.getX() + p1.getWidth() >= 500) {
                    p1.setX(p1.getX());
                } else {
                    p1.setX(p1.getX() + 5);
                }

            } else if (e.getCode() == KeyCode.LEFT) {
                if (p1.getX() <= 0) {
                    p1.setX(p1.getX());
                } else {
                    p1.setX(p1.getX() - 5);
                }
            } else if (e.getCode() == KeyCode.DOWN) {
                if (p1.getY() + p1.getHeight() >= 500) {
                    p1.setY(p1.getY());
                } else {
                    p1.setY(p1.getY() + 5);
                }
            } else if (e.getCode() == KeyCode.UP) {
                if (p1.getY() <= 0) {
                    p1.setY(p1.getY());
                } else {
                    p1.setY(p1.getY() - 5);
                }
            }
        });


        while (p1alive) {
            pane.getChildren().add(p1);
            for (int i = 0; i < score; i++) {
                Line line = new Line();
                line.setStroke(Color.GRAY);

                int direction = (int) (Math.random() * 2);
                if (direction == 0) {
                    line.setStartX(0);
                    line.setStartY(Math.random() * 500);
                    line.setEndX(500);
                    line.setEndY(Math.random() * 500);
                } else {
                    line.setStartX(Math.random() * 500);
                    line.setStartY(0);
                    line.setEndX(Math.random() * 500);
                    line.setEndY(500);
                }

                Timeline timeline2 = new Timeline(new KeyFrame(
                        Duration.millis(5000),
                        ae -> System.out.println("You cleared a level.")));

                Timeline timeline1 = new Timeline(new KeyFrame(
                        Duration.millis(5000),
                        ae -> {
                            line.setStroke(Color.RED);
                            timeline2.play();
                        }));
                Bounds lineBounds = line.getBoundsInLocal();
                if ((lineBounds.intersects(p1Bounds))) {
                    p1alive = false;
                    break;
                }

                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(5000),
                        ae -> {
                            pane.getChildren().add(line);
                            timeline1.play();
                        }));

                Timeline start = new Timeline(new KeyFrame(
                        Duration.millis(5000),
                        ae -> {
                            timeline.play();
                        }));
                start.play();
            }

            if (p1alive) {
                score++;
                pane.getChildren().clear();
            }
            else if(!p1alive) {
                Label ded = new Label("YOU GO AWAY U DIED");
                pane.getChildren().add(ded);
                break;
            }
            else{
                System.out.println("wot");
            }
        }


        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Laser Dodge");
        primaryStage.show();
        pane.requestFocus();
    }

}


