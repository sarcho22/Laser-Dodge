import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class AttemptAtFinalProject extends Application{
    public ArrayList playerScores;
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
        Button play = new Button("Play");
        play.setFont(new Font("Cambria", 40));
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


        play.setOnAction(e -> {
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

    public void help(Stage helpStage){
        BorderPane borderPane = new BorderPane();
        Rectangle bg = new Rectangle();
        bg.widthProperty().bind(borderPane.widthProperty());
        bg.heightProperty().bind(borderPane.heightProperty());
        bg.setFill(Color.ALICEBLUE);
        borderPane.getChildren().add(bg);

        HBox hBox1 = new HBox();
        borderPane.setTop(hBox1);
        HBox hBox2 = new HBox();
        borderPane.setBottom(hBox2);
        HBox hBox3 = new HBox();
        borderPane.setCenter(hBox3);

        Label gameTitle = new Label("\n\nInstructions");
        gameTitle.setFont(new Font("Cambria", 50));
        Button menu = new Button("Return to Menu");
        menu.setFont(new Font("Cambria", 20));
        Text instructions = new Text("");
        instructions.setFont(new Font("Cambria", 20));
        instructions.setText(
                "1. You will be a blue square,\n\tuse the arrow keys to avoid touching the lasers.\n" +
                "2. You will have 5 seconds to aviod the\n\t gray lasers until they turn red.\n" +
                "3. To score more points try to eat the\n\t purple circles that will appear on the screen.\n" +
                "4. Try to stay alive as long as you can\n\t and score the most points! Good Luck!"
        );

        hBox1.getChildren().add(gameTitle);
        hBox2.getChildren().add(menu);
        hBox3.getChildren().add(instructions);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);


        menu.setOnAction(e -> {
            helpStage.close();
            Stage menuStage = new Stage();
            start(menuStage);
        });


        Scene scene = new Scene(borderPane, 500, 500);
        helpStage.setScene(scene);
        helpStage.setTitle("Game Help");
        helpStage.show();
        borderPane.requestFocus();
    }


    public void game(Stage playStage) {
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
        //Button terminate = new Button("View Results");
        gridPane.add(levelName, 0, 0);
        gridPane.add(status, 1, 0);
        gridPane.add(labelScore, 2, 0);
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
            boolean p1alive = true;
            r1.setHeight(r1.getHeight() + 1);

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
                            }));

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

    public void end(Stage endStage){
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

        Label gameTitle = new Label("\n\nLeader Board");
        gameTitle.setFont(new Font("Cambria", 50));
        Button restart = new Button("Restart");
        restart.setFont(new Font("Cambria", 40));
        Button exit = new Button("Exit Game");
        exit.setFont(new Font("Cambria", 40));
        Label name = new Label("Enter Name: ");
        name.setFont(new Font("Cambria", 20));
        TextField nameEntry = new TextField();
/*
        for (int i = 0; i < playerScores.size(); i++){
            Label resultLine = new Label("\n" + playerScores.get(i) + "\t" + "points");
            resultLine.setFont(new Font("Cambria", 20));
            hBox.getChildren().add(resultLine);
        }
*/
        hBox1.getChildren().add(gameTitle);
        hBox2.getChildren().add(restart);
        hBox2.getChildren().add(exit);
        hBox3.getChildren().add(name);
        hBox3.getChildren().add(nameEntry);
        hBox.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);


        restart.setOnAction(e -> {
            endStage.close();
            Stage playStage = new Stage();
            game(playStage);
        });

        exit.setOnAction(e -> {
            endStage.close();
        });


        Scene scene = new Scene(borderPane, 500, 500);
        endStage.setScene(scene);
        endStage.setTitle("Past Results");
        endStage.show();
        borderPane.requestFocus();
    }
}
