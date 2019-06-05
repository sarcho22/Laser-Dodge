import com.sun.javafx.geom.ShapePair;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.util.Duration;

import java.util.Iterator;

public class AnotherFile extends Application {
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Rectangle p1 = new Rectangle(0, 0, 20, 20);
        p1.setFill(Color.POWDERBLUE);
        Bounds p1Bounds = p1.getBoundsInParent();

        Rectangle r1 = new Rectangle(0, 0, 0, 0);

        EventHandler<ActionEvent> eventHandler = e -> {

            int score = 1;
            boolean p1alive = true;

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
                            Duration.millis(2000),
                            ae -> {
                                System.out.println("You cleared a level");
                            }));

                    Timeline yes = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> {
                                pane.getChildren().remove(p1);
                                pane.getChildren().clear();
                                timeline2.play();
                            }));

                    Timeline no = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> {
                                Label ded = new Label("YOU GO AWAY U DIED");
                                pane.getChildren().add(ded);
                                r1.setWidth(10);
                            }));

                    Timeline check = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> {
                                boolean intersects = false;
                                for (double x = line.getStartX(); x <= line.getEndX(); x += 0.1) {
                                    double m = (line.getStartY() - line.getEndY()) / (line.getStartX() - line.getEndX());
                                    double b = line.getStartY() - (m * line.getStartX());

                                    double y = ((m * x) + b);
                                    if (p1.contains(x, y)) {
                                        System.out.println("hi");
                                        intersects = true;
                                    }
                                }
                                if (!intersects) {
                                    yes.play();
                                } else {
                                    no.play();
                                }
                            }));

                    Timeline timeline1 = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> {
                                line.setStroke(Color.RED);
                                check.play();
                            }));

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> {
                                pane.getChildren().add(line);
                                timeline1.play();
                            }));

                    Timeline start = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> {
                                timeline.play();
                            }));

                    start.play();
                    System.out.println(r1.getWidth());
                    if (r1.getWidth() == 10) {
                        p1alive = false;
                        break;
                    }

                }
                score++;
            }

        };

        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(500), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

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


        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Laser Dodge");
        primaryStage.show();
        pane.requestFocus();
    }

}


