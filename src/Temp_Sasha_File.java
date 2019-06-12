import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Temp_Sasha_File {
    public void start(Stage menuStage){
        BorderPane borderPane = new BorderPane();
        Rectangle bg = new Rectangle();
        bg.widthProperty().bind(borderPane.widthProperty());
        bg.heightProperty().bind(borderPane.heightProperty());
        bg.setFill(Color.ALICEBLUE);
        borderPane.getChildren().add(bg);

        BorderPane bp1 = new BorderPane();
        borderPane.setTop(bp1);

        HBox hBox = new HBox();
        bp1.setBottom(hBox);
        HBox hBox1 = new HBox();
        bp1.setTop(hBox1);
        HBox hBox2 = new HBox();
        borderPane.setBottom(hBox2);
        HBox hBox3 = new HBox();
        borderPane.setCenter(hBox3);
        hBox2.setSpacing(10);

        Label credits = new Label("Made by GoAway, IsGone, and JustDED");
        credits.setFont(new Font("Cambria", 20));
        Label gameTitle = new Label("\n\nLaser Dodge");
        gameTitle.setFont(new Font("Cambria", 50));
        Button one_play = new Button("Play");
        one_play.setFont(new Font("Cambria", 40));
        Button two_play = new Button("Play");
        two_play.setFont(new Font("Cambria", 40));
        Button help = new Button("Help");
        help.setFont(new Font("Cambria", 40));
        Label name = new Label("Enter Name: ");
        name.setFont(new Font("Cambria", 20));
        TextField nameEntry = new TextField();

        hBox1.getChildren().add(credits);
        hBox.getChildren().add(gameTitle);
        hBox2.getChildren().add(play);
        hBox2.getChildren().add(help);
        hBox3.getChildren().add(name);
        hBox3.getChildren().add(nameEntry);
        hBox.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);


        one_play.setOnAction(e -> {
            // shove the name into the results page!!!
            menuStage.close();
            Stage playStage = new Stage();
            single_game(playStage);
            //playerScores.add(0, nameEntry.getText())
            //playerScores.add(nameEntry.getText());
        });

        two_play.setOnAction(e -> {
            // shove the name into the results page!!!
            menuStage.close();
            Stage playStage = new Stage();
            game(playStage);
            //playerScores.add(0, nameEntry.getText())
            //playerScores.add(nameEntry.getText());
        });

        help.setOnAction(e -> {
            // put code to run the help page here
            menuStage.close();
            Stage helpStage = new Stage();
            help(helpStage);

        });


        Scene scene = new Scene(borderPane, 500, 500);
        menuStage.setScene(scene);
        menuStage.setTitle("Welcome to Laser Dodge!");
        menuStage.show();
        borderPane.requestFocus();
    }

    public void single_game(Stage playStage) {
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
        //Button terminate = new Button("View Results");
        gridPane.add(levelName, 0, 0);
        gridPane.add(status, 1, 0);
        gridPane.add(labelScore, 2, 0);
        gridPane.add(eaten, 3, 0);
        //gridPane.add(terminate, 3, 0);
        gridPane.setHgap(25);

        Rectangle p1 = new Rectangle(0, 0, 20, 20);
        p1.setFill(Color.POWDERBLUE);
/*
        terminate.setOnMouseClicked(a -> {
            playStage.close();
            //playerScores.add(score);
            Stage endStage = new Stage();
            end(endStage);
        });
*/
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

                    Timeline pending = new Timeline(new KeyFrame(
                            Duration.millis(500),
                            ae -> {
                                pane.getChildren().clear();
                                status.setText("Status pending...");
                                eaten.setText("Eaten: 0");
                                eatenNumber = 0;
                            }));

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

                    Timeline wait = new Timeline(new KeyFrame(
                            Duration.millis(500),
                            ae -> {
                                pane.getChildren().clear();
                                status.setText("Restarting the game now... \\(OwO)/");
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
        playStage.setScene(scene);
        playStage.setTitle("Laser Dodge");
        playStage.show();
        pane.requestFocus();
    }

}