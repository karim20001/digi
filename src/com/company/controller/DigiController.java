package com.company.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
    private TextField search;

    @FXML
    private Button searchBT;

    @FXML
    private ScrollPane SP;

    @FXML
    private AnchorPane ANP;

    @FXML
    private AnchorPane AP2;

    @FXML
    private AnchorPane treeANP;

    @FXML
    private GridPane GP;

    private int check_button = 1;
    private final ArrayList<TextField> textFields = new ArrayList<>();
    private final ArrayList<Double> rightValues = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchBT.setOnMouseEntered(e -> searchBT.setStyle("-fx-background-color: #2d7945;"));
        searchBT.setOnMouseExited(e -> searchBT.setStyle("-fx-background-color: #2b342b;"));

        submit.setOnMouseEntered(e -> submit.setStyle("-fx-background-color: #2d7945;"));
        submit.setOnMouseExited(e -> submit.setStyle("-fx-background-color: #2b342b;"));
        submit.setOnAction(e -> makingTree());

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
                AP2.setVisible(false);
                ANP.setVisible(true);
                result.setStyle("-fx-background-color: black");
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
                AP2.setVisible(true);
                ANP.setVisible(false);
                input.setStyle("-fx-background-color: black");
            }
            check_button = 2;
        });
    }

    public void makingTree() {
        rightValues.clear();
        int counter_of_null = 0;
        for (TextField textField : textFields){
            if (!textField.getText().equals("")){
                String[] string = textField.getText().split(" ");
                double sum = 0;
                if (string.length == 3) {
                    for (int i = 0; i < 3; i++) {
                        sum += Math.pow(Double.parseDouble(string[i]), 2);
                    }
                }
                else {
                    sum = -1;
                }
                rightValues.add(sum);
            }
            else
                rightValues.add(-1.0);
        }
        if (rightValues.size() > 0) {
            makingBST_tree();
//            show_on_GUI();
        }
    }

    private void makingBST_tree() {
        int first_min = -1;
        int second_min = -1;
        int temp = -1;
        for (int i = 0; i < rightValues.size(); i++){
            if (rightValues.get(i) != -1) {
                if (first_min == -1 || rightValues.get(first_min) > rightValues.get(i)) {
                    temp = first_min;
                    first_min = i;
                    if (second_min == -1) {
                        if (temp != -1)
                            second_min = temp;
                    }
                    if (temp != -1 && second_min != -1) {
                        if (rightValues.get(temp) < rightValues.get(second_min)) {
                            second_min = temp;
                        }
                    }
                } else if (second_min == -1 || rightValues.get(second_min) > rightValues.get(i))
                    second_min = i;
            }

        }
//        int[] counter_of_null = new int[2];
//
//        int check = 0;
//        if (first_min > second_min && second_min != -1)
//            check = 1;
//        for (int i = 0; i < rightValues.size(); i++){
//            if (rightValues.get(i) == -1)
//                counter_of_null[check]++;
//            if (rightValues.get(i) == rightValues.get(first_min)) {
//                if (first_min > second_min && second_min != -1)
//                    break;
//                else
//                    check++;
//            }
//            if (rightValues.get(i) == rightValues.get(second_min)){
//                if (second_min < first_min && second_min != -1)
//                    check--;
//                else
//                    break;
//            }
//        }

        System.out.println(textFields.get(first_min).getText());
        System.out.println(textFields.get(second_min).getText());

    }

    private void show_on_GUI() {

        float countByTwo = -1;
        for (int i = rightValues.size(); i >= 1; i /= 2 )
            countByTwo++;

        float layoutY = 10;
        float layoutX = countByTwo * 200;
        float temp = layoutX;
        double s = 1;
        for (int i = 0, j = 1; i < rightValues.size(); i++){



            Label text = new Label(Double.toString(rightValues.get(i)));
            text.setStyle("-fx-text-fill: white");

            Circle circle = new Circle(30, 30, 30);
            circle.setStyle("-fx-background-color: blue");

            if (i == Math.pow(2, j) - 1){
                j++;
                layoutY += 100;
                s = layoutX - (layoutX / 2);
                layoutX /= 2;
                countByTwo--;
                temp = layoutX;
            }


            Line rightLine = new Line();
            Line leftLine = new Line();
            if (countByTwo > 0) {
                leftLine.setStartX(0);
                leftLine.setEndX((layoutX - layoutX / 2) * -1 + 40);
                leftLine.setStartY(-55);
                leftLine.setEndY(0);
                leftLine.setLayoutY(layoutY + 110);
                leftLine.setLayoutX(temp + 15);
                leftLine.setStrokeWidth(2);

                rightLine.setStrokeWidth(2);

                leftLine.setStroke(Color.WHITE);
                rightLine.setStroke(Color.WHITE);
                rightLine.setEndX(30);
                rightLine.setStartX((layoutX - layoutX / 2));
                rightLine.setStartY(70);
                rightLine.setLayoutX(temp + 15);
                rightLine.setLayoutY(layoutY + 55);
                rightLine.setEndY(0);
            }

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(circle, text);
            stackPane.setLayoutX(temp);
            stackPane.setLayoutY(layoutY);

            temp += s * 2;
            treeANP.getChildren().addAll(stackPane, leftLine, rightLine);
        }
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
