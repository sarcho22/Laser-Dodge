import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class DoNotTouchThisFileWithoutPermission extends Application {
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Rectangle p1 = new Rectangle(0, 0, 20, 20);
        p1.setFill(Color.POWDERBLUE);

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

        Rectangle r1 = new Rectangle(0, 0, 0, 0);

        EventHandler<ActionEvent> eventHandler = e -> {
            int score = 0;
            boolean p1alive = true;
            r1.setHeight(r1.getHeight() + 1);

            while (p1alive) {
                score++;
                System.out.println(r1.getHeight());
                //
                pane.getChildren().add(p1);
                for (int i = 0; i < r1.getHeight()/10; i++) {
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
                            ae -> System.out.println("You cleared a level.")));

                    Timeline clear = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> {
                                pane.getChildren().clear();
                                r1.setWidth(10);
                            }));

                    Timeline wait = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> {
                                pane.getChildren().clear();
                                Label restart = new Label("Restarting the game now...");
                                pane.getChildren().add(restart);
                                clear.play();
                            }));

                    Timeline yes = new Timeline(new KeyFrame(
                            Duration.millis(1),
                            ae -> {
                                pane.getChildren().clear();
                                timeline2.play();
                            }));

                    Timeline no = new Timeline(new KeyFrame(
                            Duration.millis(1),
                            ae -> {
                                Label ded = new Label("YOU GO AWAY U DIED");
                                pane.getChildren().add(ded);
                                r1.setHeight(0);
                                wait.play();
                            }));

                    Timeline check = new Timeline(new KeyFrame(
                            Duration.millis(200),
                            ae -> {
                                boolean intersects = false;
                                for (double x = line.getStartX(); x <= line.getEndX(); x += 0.1) {
                                    double m = (line.getStartY() - line.getEndY()) / (line.getStartX() - line.getEndX());
                                    double b = line.getStartY() - (m * line.getStartX());
                                    double y = (m * x) + b;
                                    if (p1.contains(x, y)) {
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
                            Duration.millis(2500),
                            ae -> {
                                line.setStroke(Color.RED);
                                check.play();
                            }));

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(4000),
                            ae -> {
                                pane.getChildren().add(line);
                                timeline1.play();
                            }));

                    timeline.play();

                    if (r1.getWidth() == 10) {
                        p1alive = false;
                        break;
                    }

                }

            }
        };

        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(500), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();




        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Laser Dodge");
        primaryStage.show();
        pane.requestFocus();
    }

}


