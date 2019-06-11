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

public class TwoPlayer extends Application {
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
        gridPane.add(levelName, 0, 0);
        gridPane.add(status, 1, 0);
        gridPane.add(labelScore, 2, 0);
        gridPane.setHgap(25);

        Rectangle p1 = new Rectangle(0, 240, 20, 20);
        p1.setFill(Color.POWDERBLUE);

        Rectangle p2 = new Rectangle(480, 240, 20, 20);
        p2.setFill(Color.ALICEBLUE);

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
            if (e.getText().equals("d")) {
                if (p2.getX() + p2.getWidth() >= 500) {
                    p2.setX(p2.getX());
                } else {
                    p2.setX(p2.getX() + 5);
                }

            } else if (e.getText().equals("a")) {
                if (p2.getX() <= 0) {
                    p2.setX(p2.getX());
                } else {
                    p2.setX(p2.getX() - 5);
                }
            } else if (e.getText().equals("s")) {
                if (p2.getY() + p2.getHeight() >= 500) {
                    p2.setY(p2.getY());
                } else {
                    p2.setY(p2.getY() + 5);
                }
            } else if (e.getText().equals("w")) {
                if (p2.getY() <= 0) {
                    p2.setY(p2.getY());
                } else {
                    p2.setY(p2.getY() - 5);
                }
            }
        });

        Rectangle r1 = new Rectangle(0, 0, 0, 0);

        EventHandler<ActionEvent> eventHandler = e -> {
            boolean p1alive = true;
            boolean p2alive = true;
            r1.setHeight(r1.getHeight() + 1);

            while (p1alive && p2alive) {
                labelScore.setText("Score: " + (int)(r1.getHeight()-1));
                levelName.setText("Level " + (int)(r1.getHeight()));
                //
                pane.getChildren().add(p1);
                pane.getChildren().add(p2);

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

                    Timeline pending = new Timeline(new KeyFrame(
                            Duration.millis(500),
                            ae -> {
                                pane.getChildren().clear();
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
                                for (double x = line.getStartX(); x <= line.getEndX(); x += 0.01) {
                                    double m = (line.getStartY() - line.getEndY()) / (line.getStartX() - line.getEndX());
                                    double b = line.getStartY() - (m * line.getStartX());
                                    double y = (m * x) + b;
                                    if (p2.contains(x, y)) {
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