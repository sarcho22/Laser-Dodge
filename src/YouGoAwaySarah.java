import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.sql.Time;

// maybe make a pacman type thing where there are dots and we need to get the dots as a challenge to increase score, also high score
public class YouGoAwaySarah extends Application {
    public int eatenNumber = 0;
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        pane.prefWidthProperty().bind(borderPane.widthProperty());
        pane.prefHeightProperty().bind(borderPane.heightProperty().divide(25).multiply(24));

        GridPane gridPane = new GridPane();
        borderPane.setTop(gridPane);
        gridPane.prefWidthProperty().bind(borderPane.widthProperty());
        gridPane.prefHeightProperty().bind(borderPane.heightProperty().divide(25));

        Label levelName = new Label();
        Label status = new Label("Status pending...");
        Label labelScore = new Label();
        Label eaten = new Label("Eaten: 0");
        gridPane.add(levelName, 0, 0);
        gridPane.add(status, 1, 0);
        gridPane.add(labelScore, 2, 0);
        gridPane.add(eaten, 3, 0);
        gridPane.setHgap(25);

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
                if (p1.getY() + p1.getHeight() >=480) {
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

            boolean p1alive = true;
            r1.setHeight(r1.getHeight() + 1);

            Circle eat = new Circle((Math.random()*480)+10, Math.random()*490,10);
            eat.setFill(Color.MEDIUMPURPLE);
            eat.setStroke(Color.LIGHTBLUE);
            pane.getChildren().add(eat);

            while (p1alive) {
                labelScore.setText("Score: " + (int)(r1.getHeight()-1));
                levelName.setText("Level " + (int)(r1.getHeight()));
                //
                pane.getChildren().add(p1);
                for (int i = 0; i < r1.getHeight(); i++) {
                    Line line = new Line();
                    line.setStroke(Color.GRAY);

                    int direction = (int) (Math.random() * 10);
                    if (direction == 0||direction == 7||direction == 8) {
                        line.setStartX(0);
                        line.setStartY(Math.random() * 500);
                        line.setEndX(500);
                        line.setEndY(Math.random() * 500);
                    }
                    else if (direction == 2){
                        line.setStartX(0);
                        line.setStartY(Math.random() * 500);
                        line.setEndX(Math.random() * 500);
                        line.setEndY(500);
                    }
                    else if (direction == 3) {
                        line.setStartX(500);
                        line.setStartY(Math.random() * 500);
                        line.setEndX(Math.random() * 500);
                        line.setEndY(0);
                    }
                    else if (direction == 4) {
                        line.setStartX(0);
                        line.setStartY(Math.random() * 500);
                        line.setEndX(Math.random() * 500);
                        line.setEndY(0);
                    }
                    else if (direction == 5) {
                        line.setStartX(500);
                        line.setStartY(Math.random() * 500);
                        line.setEndX(Math.random() * 500);
                        line.setEndY(500);
                    }
                    else if (direction == 9 || direction == 10){
                        line.setStartX(Math.random() * 500);
                        line.setStartY(0);
                        line.setEndX(Math.random() * 500);
                        line.setEndY(500);
                    }
                    else {
                        line.setStartX(Math.random() * 500);
                        line.setStartY(0);
                        line.setEndX(Math.random() * 500);
                        line.setEndY(500);
                    }

                    Timeline timeline2 = new Timeline(new KeyFrame(
                            Duration.millis(1000),
                            ae -> System.out.println("You cleared Level: " + (r1.getHeight()-1))));

//                    Timeline clear = new Timeline(new KeyFrame(
//                            Duration.millis(1000),
//                            ae -> {
//                                pane.getChildren().clear();
//                                r1.setHeight(1);
//                            }));

                    Timeline eating = new Timeline(new KeyFrame(
                            Duration.millis(50),
                            ae -> {
                                boolean boolEaten = false;
                                if(p1.contains(eat.getCenterX()-eat.getRadius(), eat.getCenterY())) {
                                    boolEaten = true;
                                }
                                if(p1.contains(eat.getCenterX()+eat.getRadius(), eat.getCenterY())) {
                                    boolEaten = true;
                                }
//                                for (double x = eat.getCenterX()-eat.getRadius()+0.01; x <= eat.getCenterX()+eat.getRadius()-0.01; x += 0.01) {
//                                    double y = Math.sqrt(Math.pow(x - eat.getCenterX(), 2) - Math.pow(eat.getRadius(), 2))+ eat.getCenterY();
//
//                                    double y2 = eat.getCenterY()-y;
//                                    if (p1.contains(x, y)) {
//                                        boolEaten = true;
//                                    }
//                                    if (p1.contains(x, y2)) {
//                                        boolEaten = true;
//                                    }
//                                }
////                                for (double t = 0; t < 360; t++) {
//                                    double x = (eat.getRadius() * Math.cos(t)) + eat.getCenterX();
//                                    double y = (eat.getRadius() * Math.sin(t)) + eat.getCenterY();
//                                    if (p1.contains(x, y)) {
//                                        boolEaten = true;
//                                    }
//                                }
                                if(boolEaten) {
                                    eatenNumber++;
                                    eaten.setText("Eaten: " + eatenNumber);
                                    eat.setRadius(0);
                                    eat.setCenterX(1000);
                                    eat.setCenterY(1000);
                                    pane.getChildren().remove(eat);
                                }
                            }));

                    eating.setCycleCount(Timeline.INDEFINITE);
                    eating.play();

                    // (x - x-coordinate)^2+(y - y-coordinate)^2=radius^2
                    // y-coordinate +  sqare root this side (x - x-coordinate)^2 -radius ^2 = y

                    Timeline pending = new Timeline(new KeyFrame(
                            Duration.millis(500),
                            ae -> {
                                pane.getChildren().clear();
                                eaten.setText("Eaten: 0");
                                eatenNumber = 0;
                                status.setText("Status pending...");
                            }));

                    Timeline wait = new Timeline(new KeyFrame(
                            Duration.millis(500),
                            ae -> {
                                pane.getChildren().clear();
                                status.setText("Restarting the game now... \\(OwO)/");
//                                clear.play();
                                r1.setHeight(0);
                                pending.play();
                            }));

                    Timeline yes = new Timeline(new KeyFrame(
                            Duration.millis(5),
                            ae -> {
                                pane.getChildren().clear();
                                status.setText("You cleared the level >:3");
                                timeline2.play();
                            }));

                    Timeline no = new Timeline(new KeyFrame(
                            Duration.millis(5),
                            ae -> {
                                status.setText("YOU GO AWAY YOU DED");
                                r1.setHeight(0);
                                wait.play();
                            }));

                    Timeline check = new Timeline(new KeyFrame(
                            Duration.millis(200),
                            ae -> {
                                boolean intersects = false;
                                for (double x = line.getStartX(); x <= line.getEndX(); x += 0.01) {
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
                            Duration.millis(1500),
                            ae -> {
                                line.setStroke(Color.RED);
                                check.play();
                            }));

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(1500),
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
                new KeyFrame(Duration.millis(5205), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();




        Scene scene = new Scene(borderPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Laser Dodge");
        primaryStage.show();
        pane.requestFocus();
    }
}