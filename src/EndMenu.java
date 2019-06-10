import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EndMenu extends Application {
    public void start(Stage primaryStage){
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


        restart.setOnAction(e -> {
            // put code to run the game here
            //primaryStage.close();

        });


        Scene scene = new Scene(borderPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Past Results");
        primaryStage.show();
        borderPane.requestFocus();
    }
}
