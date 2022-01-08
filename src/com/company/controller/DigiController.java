package com.company.controller;

import com.company.model.Tree;
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
import java.util.Arrays;
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
    private Button calculate;

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
    public static final ArrayList<StackPane> stackPanes = new ArrayList<>();
    Tree obj = new Tree();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchBT.setOnMouseEntered(e -> searchBT.setStyle("-fx-background-color: #2d7945;"));
        searchBT.setOnMouseExited(e -> searchBT.setStyle("-fx-background-color: #2b342b;"));

        calculate.setOnMouseEntered(e -> calculate.setStyle("-fx-background-color: #2d7945;"));
        calculate.setOnMouseExited(e -> calculate.setStyle("-fx-background-color: #2b342b;"));
        calculate.setOnAction(e -> obj.search(in2.getText().split(" ")));

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
        obj = new Tree();
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
            show_on_GUI();
        }
    }

    ArrayList<String> all_for_gui = new ArrayList<>();
    private void makingBST_tree() {
        int first_min = -1;
        int second_min = -1;
        int temp = -1;
        // find two close to (0, 0, 0)
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
                }
                else if (second_min == -1 || rightValues.get(second_min) > rightValues.get(i))
                    second_min = i;
            }
        }

        //-------------------------------------------------------------------------------------

        ArrayList<String[]> split_textFields = new ArrayList<>();
        int rooter = 0;
        for (int i = 0; i < textFields.size(); i++){
            if (i != first_min && i != second_min)
                rooter = i;
            if (textFields.get(i).getText().length() > 0)
                split_textFields.add(textFields.get(i).getText().split(" "));
        }

        ArrayList<String[]> temp_array = new ArrayList<>();

        if (textFields.size() > 2){
            // set root
            obj.insert(textFields.get(rooter).getText());
            all_for_gui.add(textFields.get(rooter).getText());

            //set near to (0, 0, 0)
            obj.root.left = new Tree.Node(textFields.get(first_min).getText());
            obj.root.right = new Tree.Node(textFields.get(second_min).getText());
            String[] go_to1 = split_textFields.get(first_min);
            temp_array.add(go_to1);
            String[] go_to = split_textFields.get(second_min);
            String[] go_rooter = split_textFields.get(rooter);
            split_textFields.remove(go_to1);
//            System.out.println(split_textFields.size());
            temp_array.add(go_to);
            split_textFields.remove(go_to);
            split_textFields.remove(go_rooter);
            all_for_gui.add(go_to1[0] + " " + go_to1[1] + " " + go_to1[2]);
            all_for_gui.add(go_to[0] + " " + go_to[1] + " " + go_to[2]);
            //--------------------------------------------------------
            // making tree
            ArrayList<Tree.Node> saving_root = new ArrayList<>();
            saving_root.add(obj.root.left);
            saving_root.add(obj.root.right);
            ArrayList<Tree.Node> temp_roots = new ArrayList<>();
            ArrayList<String[]> temp_parents = new ArrayList<>();

            do {

                double min1 = -1, min2 = -1, cast = -1;

                String[] temp1 = null, save_min1 = null, save_min2 = null;
                int going_forward = 0;
                for (String[] parents : temp_array) {

                    for (String[] childs : split_textFields) {
                        double distance = Math.sqrt(Math.pow(Double.parseDouble(childs[0]) - Double.parseDouble(parents[0]), 2) + Math.pow(Double.parseDouble(childs[1]) - Double.parseDouble(parents[1]), 2) + Math.pow(Double.parseDouble(childs[2]) - Double.parseDouble(parents[2]), 2));

                        if (min1 == -1 || min1 > distance) {

                            if (min1 != -1) {
                                temp1 = save_min1;
                                cast = min1;
                            }
                            save_min1 = childs;
                            min1 = distance;

                            if (min2 == -1) {
                                if (temp1 != null) {
                                    save_min2 = temp1;
                                    min2 = cast;
                                }
                            }
                            if (temp1 != null && min2 != -1) {
                                if (cast < min2) {
                                    save_min2 = temp1;
                                    min2 = cast;
                                }
                            }
                        } else if (min2 == -1 || min2 > distance) {
                            min2 = distance;
                            save_min2 = childs;
                        }
                    }

                    for (int i = 0; i < temp_array.size(); i++) {
                        if (save_min1 == null)
                            break;
                        double distance_from_other_parent1 = Math.sqrt(Math.pow(Double.parseDouble(save_min1[0]) - Double.parseDouble(temp_array.get(i)[0]), 2) + Math.pow(Double.parseDouble(save_min1[1]) - Double.parseDouble(temp_array.get(i)[1]), 2) + Math.pow(Double.parseDouble(save_min1[2]) - Double.parseDouble(temp_array.get(i)[2]), 2));
                        double distance_from_other_parent2 = -1;
                        if (save_min2 != null)
                            distance_from_other_parent2 = Math.sqrt(Math.pow(Double.parseDouble(save_min2[0]) - Double.parseDouble(temp_array.get(i)[0]), 2) + Math.pow(Double.parseDouble(save_min2[1]) - Double.parseDouble(temp_array.get(i)[1]), 2) + Math.pow(Double.parseDouble(save_min2[2]) - Double.parseDouble(temp_array.get(i)[2]), 2));
                        if (distance_from_other_parent1 < min1)
                            min1 = -1;
                        if (distance_from_other_parent2 < min2 && min2 != -1)
                            min2 = -1;
                    }

                    if (min1 != -1) {
                        temp_parents.add(save_min1);
                        saving_root.get(going_forward).left = new Tree.Node(save_min1[0] + " " + save_min1[1] + " " + save_min1[2]);
                        temp_roots.add(saving_root.get(going_forward).left);
                        split_textFields.remove(save_min1);
                        all_for_gui.add(save_min1[0] + " " + save_min1[1] + " " + save_min1[2]);
                    } else {
                        saving_root.get(going_forward).left = new Tree.Node(null);
                        all_for_gui.add(null);
                    }
                    if (min2 != -1) {
                        temp_parents.add(save_min2);
                        saving_root.get(going_forward).right = new Tree.Node(save_min2[0] + " " + save_min2[1] + " " + save_min2[2]);
                        temp_roots.add(saving_root.get(going_forward).right);
                        split_textFields.remove(save_min2);
                        all_for_gui.add(save_min2[0] + " " + save_min2[1] + " " + save_min2[2]);
                    } else {
                        saving_root.get(going_forward).right = new Tree.Node(null);
                        all_for_gui.add(null);
                    }
                    save_min1 = null;
                    save_min2 = null;
                    temp1 = null;
                    min1 = -1;
                    min2 = -1;
                    cast = -1;
                    going_forward++;
                }
                temp_array.clear();
                temp_array.addAll(temp_parents);
//                System.out.println(Arrays.toString(temp_parents));
                saving_root.clear();
                saving_root.addAll(temp_roots);
                temp_roots.clear();
                temp_parents.clear();

            } while (split_textFields.size() != 0);
//            System.out.println(obj.root.left.left.key);
        }
    }

    private void show_on_GUI() {

        treeANP.getChildren().clear();

        ArrayList<Tree.Node> temp_for_gui = new ArrayList<>();
        ArrayList<Tree.Node> head = new ArrayList<>();
        ArrayList<Tree.Node> leaf = new ArrayList<>();

        head.add(obj.root);
        leaf.add(obj.root.left);
        leaf.add(obj.root.right);
//        System.out.println(obj.root.left.left.right);
obj.inorder();
        float countByTwo = -1;
        for (int i = all_for_gui.size(); i >= 1; i /= 2 )
            countByTwo++;

        float layoutY = 10;
        float layoutX = countByTwo * 200;
        float temp = layoutX;
        double s = 1;
        for (int i = 0, j = 1, k = 0, counter = 0; ; i++, k++, counter += 2) {

            boolean checker = true;
            if (i == Math.pow(2, j) - 1) {
                j++;
                layoutY += 100;
                s = layoutX - (layoutX / 2);
                layoutX /= 2;
                countByTwo--;
                temp = layoutX;

                head.clear();
                head.addAll(leaf);
                leaf.clear();
                leaf.addAll(temp_for_gui);
                temp_for_gui.clear();
                k = 0;
                counter = 0;
            }
            for (Tree.Node x : head){
                if (x != null){
                    if (x.key != null)
                        checker = false;
                }
            }
            if (checker)
                break;


            Label text = new Label();
            text.setStyle("-fx-text-fill: white");

            Circle circle = new Circle(30, 30, 30);
            circle.setStyle("-fx-background-color: blue");


            Line rightLine = new Line();
            Line leftLine = new Line();
            if (head.get(k) != null && head.get(k).key != null) {
//                System.out.println(head.get(k).key);
                text.setText(head.get(k).key);
//                if (countByTwo > 0) {
                    if (leaf.get(counter) != null) {
                        leftLine.setStartX(0);
                        leftLine.setEndX((layoutX - layoutX / 2) * -1 + 40);
                        leftLine.setStartY(-55);
                        leftLine.setEndY(0);
                        leftLine.setLayoutY(layoutY + 110);
                        leftLine.setLayoutX(temp + 15);
                        leftLine.setStrokeWidth(2);
                        leftLine.setStroke(Color.WHITE);
                    }
                    if (leaf.get(counter + 1) != null) {
                        rightLine.setStrokeWidth(2);
                        rightLine.setStroke(Color.WHITE);
                        rightLine.setEndX(30);
                        rightLine.setStartX((layoutX - layoutX / 2));
                        rightLine.setStartY(70);
                        rightLine.setLayoutX(temp + 15);
                        rightLine.setLayoutY(layoutY + 55);
                        rightLine.setEndY(0);
                    }
//                }

                StackPane stackPane = new StackPane();
//                    circle.setFill(Color.BLUEVIOLET);
                stackPane.getChildren().addAll(circle, text);
                stackPane.setLayoutX(temp);
                stackPane.setLayoutY(layoutY);
//                stackPane.setStyle("-fx-background-color: blue");
                stackPanes.add(stackPane);

                treeANP.getChildren().addAll(stackPane, leftLine, rightLine);
            }

            for (Tree.Node x : leaf){
                if (x != null) {
                    if (x.left != null) {
                        if (x.left.key != null)
                            temp_for_gui.add(x.left);
                        else
                            temp_for_gui.add(null);
//                        System.out.println(x.left.key);
                    }
                    else
                        temp_for_gui.add(null);

                    if (x.right != null) {
                        if (x.right.key != null)
                            temp_for_gui.add(x.right);
                        else
                            temp_for_gui.add(null);
                    }
                    else
                        temp_for_gui.add(null);
                }
                else {
                    temp_for_gui.add(null);
                    temp_for_gui.add(null);
                }
            }
            temp += s * 2;
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
