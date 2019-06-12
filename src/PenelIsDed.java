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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class PenelIsDed extends Application{
    public ArrayList playerScores;
    public int eatenNumber1 = 0;
    public int eatenNumber2 = 0;
    public boolean p1left = false;
    public boolean p2right = false;
    public boolean p1up = false;
    public boolean p1down = false;
    public boolean p2left = false;
    public boolean p2down = false;
    public boolean p2up = false;
    public boolean p1right = false;
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
        Label p1eaten = new Label("Player 1: 0");
        Label p2eaten = new Label("Player 2: 0");
        gridPane.add(levelName, 1, 0);
        gridPane.add(status, 2, 0);
        gridPane.add(p1eaten, 0, 0);
        gridPane.add(p2eaten, 3, 0);
        gridPane.setHgap(25);

        Rectangle p1 = new Rectangle(480, 240, 20, 20);
        p1.setFill(Color.POWDERBLUE);

        Rectangle p2 = new Rectangle(0, 240, 20, 20);
        p2.setFill(Color.LIGHTPINK);

        Circle eye1 = new Circle();
        eye1.centerXProperty().bind(p1.xProperty().add(7));
        eye1.centerYProperty().bind(p1.yProperty().add(7));
        eye1.setRadius(3);
        eye1.setStrokeWidth(1);
        eye1.setFill(Color.BLACK);
        pane.getChildren().add(eye1);

        Circle eye2 = new Circle();

        EventHandler<ActionEvent> eventHandler2 = f -> {
            pane.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.RIGHT) {
                    p1right = true;
                } else if (e.getCode() == KeyCode.LEFT) {
                    p1left = true;
                } else if (e.getCode() == KeyCode.DOWN) {
                    p1down = true;
                } else if (e.getCode() == KeyCode.UP) {
                    p1up = true;
                }
                if (e.getText().equals("d")) {
                    p2left = true;
                } else if (e.getText().equals("a")) {
                    p2right = true;
                } else if (e.getText().equals("s")) {
                    p2down = true;
                } else if (e.getText().equals("w")) {
                    p2up = true;
                }
            });

            pane.setOnKeyReleased(e -> {
                if (e.getCode() == KeyCode.RIGHT) {
                    p1right = false;
                } else if (e.getCode() == KeyCode.LEFT) {
                    p1left = false;
                } else if (e.getCode() == KeyCode.DOWN) {
                    p1down = false;
                } else if (e.getCode() == KeyCode.UP) {
                    p1up = false;
                }
                if (e.getText().equals("d")) {
                    p2left = false;
                } else if (e.getText().equals("a")) {
                    p2right = false;
                } else if (e.getText().equals("s")) {
                    p2down = false;
                } else if (e.getText().equals("w")) {
                    p2up = false;
                }
            });

            if (p1up) {
                if (p1.getY() <= 0) {
                    p1.setY(p1.getY());
                } else {
                    p1.setY(p1.getY() - 5);
                }
            }
            if (p2up) {
                if (p2.getY() <= 0) {
                    p2.setY(p2.getY());
                } else {
                    p2.setY(p2.getY() - 5);
                }
            }
            if (p2down) {
                if (p2.getY() + p2.getHeight() >= 500) {
                    p2.setY(p2.getY());
                } else {
                    p2.setY(p2.getY() + 5);
                }
            }
            if (p1down) {
                if (p1.getY() + p1.getHeight() >= 500) {
                    p1.setY(p1.getY());
                } else {
                    p1.setY(p1.getY() + 5);
                }
            }
            if (p2left) {
                if (p2.getX() + p2.getWidth() >= 500) {
                    p2.setX(p2.getX());
                } else {
                    p2.setX(p2.getX() + 5);
                }
            }
            if (p1left) {
                if (p1.getX() <= 0) {
                    p1.setX(p1.getX());
                } else {
                    p1.setX(p1.getX() - 5);
                }
            }
            if (p2right) {
                if (p2.getX() <= 0) {
                    p2.setX(p2.getX());
                } else {
                    p2.setX(p2.getX() - 5);
                }
            }
            if (p1right) {
                if (p1.getX() + p1.getWidth() >= 500) {
                    p1.setX(p1.getX());
                } else {
                    p1.setX(p1.getX() + 5);
                }
            }
        };

        Timeline keys = new Timeline(
                new KeyFrame(Duration.millis(50), eventHandler2));
        keys.setCycleCount(Timeline.INDEFINITE);
        keys.play();

        Rectangle r1 = new Rectangle(0, 0, 0, 0);

        EventHandler<ActionEvent> eventHandler = e -> {

            boolean p1alive = true;
            boolean p2alive = true;
            r1.setHeight(r1.getHeight() + 1);

            Circle eat = new Circle((Math.random()*470)+10, Math.random()*470,10);
            eat.setFill(Color.MEDIUMPURPLE);
            eat.setStroke(Color.LIGHTBLUE);
            pane.getChildren().add(eat);

            while (p1alive && p2alive) {
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

                    Timeline eating = new Timeline(new KeyFrame(
                            Duration.millis(50),
                            ae -> {
                                boolean boolEaten1 = false;
                                boolean boolEaten2 = false;
                                if(p1.contains(eat.getCenterX()-eat.getRadius(), eat.getCenterY())) {
                                    boolEaten1 = true;
                                }
                                if(p1.contains(eat.getCenterX()+eat.getRadius(), eat.getCenterY())) {
                                    boolEaten1 = true;
                                }

                                if(p2.contains(eat.getCenterX()-eat.getRadius(), eat.getCenterY())) {
                                    boolEaten2 = true;
                                }
                                if(p2.contains(eat.getCenterX()+eat.getRadius(), eat.getCenterY())) {
                                    boolEaten2 = true;
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
                                if(boolEaten1) {
                                    eatenNumber1++;
                                    p2eaten.setText("Player 2: " + eatenNumber1);
                                    eat.setRadius(0);
                                    eat.setCenterX(1000);
                                    eat.setCenterY(1000);
                                    pane.getChildren().remove(eat);
                                }
                                if(boolEaten2) {
                                    eatenNumber2++;
                                    p1eaten.setText("Player 1: " + eatenNumber2);
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
                                p2eaten.setText("Player 2: 0");
                                p1eaten.setText("Player 1: 0");
                                eatenNumber1 = 0;
                                eatenNumber2 = 0;
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
                                    if (p1.contains(x, y) || p2.contains(x, y)) {
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
                        p2alive = false;
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
