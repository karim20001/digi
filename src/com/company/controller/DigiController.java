package com.company.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DigiController implements Initializable {

    @FXML
    private Button plus;

    @FXML
    private Button submit;

    @FXML
    private Button remove;

    @FXML
    private Button input;

    @FXML
    private Button result;

    @FXML
    private TextField in1;

    @FXML
    private TextField in2;

    @FXML
    private ScrollPane SP;

    @FXML
    private AnchorPane ANP;

    @FXML
    private GridPane GP;

    private int check_button = 1;
    private final ArrayList<TextField> textFields = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        submit.setOnMouseEntered(e -> submit.setStyle("-fx-background-color: #2d7945;"));
        submit.setOnMouseExited(e -> submit.setStyle("-fx-background-color: #2b342b;"));

        plus.setOnMouseEntered(e -> plus.setStyle("-fx-background-color: #2d7945;"));
        plus.setOnMouseExited(e -> plus.setStyle("-fx-background-color: #2b342b;"));
        plus.setOnAction(e -> addNew_field());

        remove.setOnMouseEntered(e -> remove.setStyle("-fx-background-color: #2d7945;"));
        remove.setOnMouseExited(e -> remove.setStyle("-fx-background-color: #2b342b;"));
        remove.setOnAction(e -> removeNode());

        in1.textProperty().addListener(e -> create_input());

        input.setOnMouseEntered(e -> {
            if (check_button != 1){
                input.setStyle("-fx-background-color: #2d87c2");
            }
        });
        input.setOnMouseExited(e -> {
            if (check_button != 1)
                input.setStyle("-fx-background-color: black");
        });
        input.setOnAction(e -> {
            if (check_button != 1){
                input.setStyle("-fx-background-color: #044b7a");
                check_button = 1;
            }
        });
        result.setOnMouseEntered(e -> {
            if (check_button != 2){
                result.setStyle("-fx-background-color: #2d87c2");
            }
        });
        result.setOnMouseExited(e -> {
            if (check_button != 2)
                result.setStyle("-fx-background-color: black");
        });
        result.setOnAction(e -> {
            if (check_button != 2){
                result.setStyle("-fx-background-color: #044b7a");
            }
        });
    }

    public void removeNode() {

        if (textFields.size() > 0) {
            GP.getChildren().remove(textFields.get(textFields.size() - 1));
            textFields.remove(textFields.get(textFields.size() - 1));
        }
    }

    public void addNew_field() {

        TextField textField = new TextField();
        textField.setStyle("-fx-background-color: #8f8d8d; -fx-text-fill: white");
        textFields.add(textField);

        int row = GP.getRowCount();
        int col = GP.getChildren().size() % 4;
        if (col == 0)
            row++;
        GP.add(textField, col, row - 1);
    }

    public void create_input (){

        GP.getChildren().clear();
        textFields.clear();

        if (Objects.equals(in1.getText(), "")) return;

        int number = Integer.parseInt(in1.getText());
        if (number < 1) return;

        for (int i = 0, row = 0, col = 0; i < number; i++, col++){
            TextField textField = new TextField();
            textField.setStyle("-fx-background-color: #8f8d8d; -fx-text-fill: white");
            textFields.add(textField);

            GP.add(textField, col, row);

            if ((i + 1) %4 == 0 && i != 0){
                row++;
                col = -1;
            }
        }
    }

}
