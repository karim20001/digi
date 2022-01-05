package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application{

    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("view/project5.fxml")));
        stage.setScene(new Scene(root));
        stage.setTitle("digikala");
        stage.show();
    }
}
