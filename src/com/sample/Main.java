package com.sample;

import com.Stok.Test;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args) {
        launch(args);
    }

    Stage window;
    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();
    Button button4 = new Button();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent sample = FXMLLoader.load(getClass().getResource("com/sample/sample.fxml"));
        window = primaryStage;

        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        window.setTitle("Symulacja ruchu na stoku narciarskim");

        button1.setText("Baza-szczyt");
        button1.setOnAction(this);
        button2.setText("Baza-srodek");
        button2.setOnAction(this);
        button3.setText("Srodek-szczyt");
        button3.setOnAction(this);
        button4.setText("Zamknij program");
        button4.setOnAction(this);

        VBox layout = new VBox();
        layout.getChildren().addAll(button1, button2, button3, button4);
        layout.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(layout, 700, 600);
        window.setScene(scene);
        window.show();


    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button1) {
            Test.num[0]++;
        } else if (actionEvent.getSource() == button2) {
            Test.num[1]++;
        } else if (actionEvent.getSource() == button3) {
            Test.num[2]++;
        } else if (actionEvent.getSource() == button4) {
            window.close();
        }
    }
}
