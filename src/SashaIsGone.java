/*import javafx.application.Application;
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

public class SashaIsGone extends Application {
    public void start (Stage primaryStage) {
        Pane pane = new Pane();
/*
        Rectangle board = new Rectangle();
        board.setFill(Color.BEIGE);
        board.setX(0);
        board.setY(0);
        board.widthProperty().bind(pane.widthProperty());
        board.heightProperty().bind(pane.heightProperty());
        pane.getChildren().add(board);
*/
/*
        Rectangle p1 = new Rectangle(20, 20, 20, 20);
        p1.setFill(Color.POWDERBLUE);
        Bounds p1Bounds = p1.getBoundsInParent();
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

        pane.getChildren().add(p1);

        while (p1alive) {
            for (int i = 0; i < score; i++) {

                Line line = new Line();

                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(5000),
                   ae -> {
                            pane.getChildren().add(line);
                            line.setStroke(Color.GRAY);
                   }));
                        timeline.play();

                Bounds lineBounds = line.getBoundsInParent();
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

                Timeline timeline1 = new Timeline(new KeyFrame(
                        Duration.millis(5000),
                        ae -> line.setStroke(Color.RED)));
                timeline1.play();

                if (isIntersect(p1Bounds, lineBounds)) {
                    p1alive = false;
                    break;
                }
            }

            if (p1alive) {
                score++;
                pane.getChildren().clear();
            } else {
                Label ded = new Label("YOU GO AWAY U DIED");
                pane.getChildren().add(ded);
                break;
            }
        }


        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Laser Dodgeball");
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
*/


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

import java.util.concurrent.TimeUnit;

public class SashaIsGone extends Application {
    public void start (Stage primaryStage) {
        Pane pane = new Pane();

        Rectangle p1 = new Rectangle();
        p1.setFill(Color.POWDERBLUE);
        p1.setWidth(20);
        p1.setHeight(20);
        Bounds p1Bounds = p1.getBoundsInParent();

        int score = 0;
        boolean p1alive = true;

        while (p1alive) {
            Line line = new Line();
            line.setStroke(Color.GRAY);

            Timeline t1 = new Timeline(new KeyFrame(
                    Duration.millis(2000),
                    ae -> {}));

            pane.getChildren().add(line);
            int direction = (int) (Math.random() * 2);
            if (direction == 0) {
                line.setStartX(0);
                line.setStartY(Math.random() * 500);
                line.setEndX(500);
                line.setEndY(Math.random() * 500);
            }
            else {
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

            if (isIntersect(p1, line)) {
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

        if(p1alive) {
            score++;
            pane.getChildren().clear();
        }
        else if(!p1alive) {
            Label ded = new Label("YOU GO AWAY U DIED");
            pane.getChildren().add(ded);
        }
        else {
            System.out.println("wot");
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
    public boolean isIntersect(Rectangle p1, Line line) {
        if (p1.getBoundsInParent().intersects(line.getBoundsInParent())) {
            return true;
        }
        return false;
    }
}
