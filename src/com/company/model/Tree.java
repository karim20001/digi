package com.company.model;

import com.company.controller.DigiController;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Tree {

    //node class that defines BST node
    public static class Node {
        public String key;
        public Node left, right;

        public Node(String data){
            key = data;
            left = right = null;
        }
    }
    // BST root node
    public Node root;

    // Constructor for BST =>initial empty tree
    public Tree(){
        root = null;
    }

    public void search (String[] search_val){

        if (DigiController.stackPanes.size() > 0)
            for (StackPane x : DigiController.stackPanes){
                Circle circle = (Circle) x.getChildren().get(0);
                circle.setFill(Color.BLACK);
                circle.setStroke(Color.BLACK);
            }

        String[] repair_shop = root.key.split(" ");

        double main_distance = Math.pow(Double.parseDouble(search_val[0]) - Double.parseDouble(repair_shop[0]), 2) + Math.pow(Double.parseDouble(search_val[1]) - Double.parseDouble(repair_shop[1]), 2) + Math.pow(Double.parseDouble(search_val[2]) - Double.parseDouble(repair_shop[2]), 2);
        String[] destination = repair_shop;
        ArrayList<Node> same_distance = new ArrayList<>();
//        ArrayList<String[]> nearest = new ArrayList<>();
        set_search_color(repair_shop, null);

        Node root_child = root;
        while (true){

            String[] right = null, left = null, right_right = null, right_left = null, left_right = null, left_left = null;
            double compare_right = -1, compare_right_right = -1, compare_right_left = -1;
            double compare_left = -1, compare_left_left = -1, compare_left_right = -1;

            if (root_child.right != null) {
                if (root_child.right.key != null) {
                    right = root_child.right.key.split(" ");
                    compare_right = Math.pow(Double.parseDouble(search_val[0]) - Double.parseDouble(right[0]), 2) + Math.pow(Double.parseDouble(search_val[1]) - Double.parseDouble(right[1]), 2) + Math.pow(Double.parseDouble(search_val[2]) - Double.parseDouble(right[2]), 2);
                }
                if (root_child.right.right != null) {
                    if (root_child.right.right.key != null) {
                        right_right = root_child.right.right.key.split(" ");
                        compare_right_right = Math.pow(Double.parseDouble(search_val[0]) - Double.parseDouble(right_right[0]), 2) + Math.pow(Double.parseDouble(search_val[1]) - Double.parseDouble(right_right[1]), 2) + Math.pow(Double.parseDouble(search_val[2]) - Double.parseDouble(right_right[2]), 2);
                    }
                }
                if (root_child.right.left != null) {
                    if (root_child.right.left.key != null) {
                        right_left = root_child.right.left.key.split(" ");
                        compare_right_left = Math.pow(Double.parseDouble(search_val[0]) - Double.parseDouble(right_left[0]), 2) + Math.pow(Double.parseDouble(search_val[1]) - Double.parseDouble(right_left[1]), 2) + Math.pow(Double.parseDouble(search_val[2]) - Double.parseDouble(right_left[2]), 2);
                    }
                }
            }
            if (root_child.left != null) {
                if (root_child.left.key != null) {
                    left = root_child.left.key.split(" ");
                    compare_left = Math.pow(Double.parseDouble(search_val[0]) - Double.parseDouble(left[0]), 2) + Math.pow(Double.parseDouble(search_val[1]) - Double.parseDouble(left[1]), 2) + Math.pow(Double.parseDouble(search_val[2]) - Double.parseDouble(left[2]), 2);
                }
                if (root_child.left.left != null)
                    if (root_child.left.left.key != null){
                        left_left = root_child.left.left.key.split(" ");
                        compare_left_left = Math.pow(Double.parseDouble(search_val[0]) - Double.parseDouble(left_left[0]), 2) + Math.pow(Double.parseDouble(search_val[1]) - Double.parseDouble(left_left[1]), 2) + Math.pow(Double.parseDouble(search_val[2]) - Double.parseDouble(left_left[2]), 2);
                    }
                if (root_child.left.right != null)
                    if (root_child.left.right.key != null){
                        left_right = root_child.left.right.key.split(" ");
                        compare_left_right = Math.pow(Double.parseDouble(search_val[0]) - Double.parseDouble(left_right[0]), 2) + Math.pow(Double.parseDouble(search_val[1]) - Double.parseDouble(left_right[1]), 2) + Math.pow(Double.parseDouble(search_val[2]) - Double.parseDouble(left_right[2]), 2);
                    }
            }

            ArrayList<Double> temp = new ArrayList<>();
            temp.add(compare_left); temp.add(compare_left_left); temp.add(compare_left_right);
            temp.add(compare_right); temp.add(compare_right_right); temp.add(compare_right_left);

            ArrayList<String[]> temp1 = new ArrayList<>();
            temp1.add(left); temp1.add(left_left); temp1.add(left_right);
            temp1.add(right); temp1.add(right_right); temp1.add(right_left);

            set_search_color(null, temp1);

            double set_right_left = -1;
            boolean equal = false;
            for (Double x : temp){
                System.out.println(x);
                if ((x < set_right_left || set_right_left == -1) && x != -1) {
                    set_right_left = x;
                    equal = false;
                }
                else if (set_right_left != -1 && x == set_right_left)
                    equal = true;
            }
            if (equal)
                same_distance.add(root_child.right);

            if (set_right_left != -1 && temp.indexOf(set_right_left) < 3){
                root_child = root_child.left;
                System.out.println(465);
                if (set_right_left < main_distance){
                    main_distance = set_right_left;
                    destination = temp1.get(temp.indexOf(set_right_left));
                }
            }
            if (set_right_left != -1 && temp.indexOf(set_right_left) > 2){
                root_child = root_child.right;
                if (set_right_left < main_distance){
                    main_distance = set_right_left;
                    destination = temp1.get(temp.indexOf(set_right_left));
                }
            }



//            if (compare_left >= 0 && compare_right >= 0){
//                if (compare_left < compare_right){
//                    root_child = root_child.left;
//                    if (compare_left < main_distance) {
//                        main_distance = compare_left;
//                        destination = left;
//                    }
//                }
//                else if (compare_right < compare_left){
//                    root_child = root_child.right;
//                    if (compare_right < main_distance) {
//                        main_distance = compare_right;
//                        destination = right;
//                    }
//                }
//                if (compare_left == compare_right){
//
//                    if (main_distance > compare_left) {
//                        main_distance = compare_left;
//                        destination = left;
//                    }
//                    same_distance.add(root_child.right);
//                    root_child = root_child.left;
//                }
//            }
//            else if (compare_left >= 0 && compare_right == -1){
//                if (main_distance > compare_left) {
//                    main_distance = compare_left;
//                    destination = left;
//                }
//                root_child = root_child.left;
//            }
//            else if (compare_left == -1 && compare_right >= 0){
//                root_child = root_child.right;
//                if (compare_right < main_distance) {
//                    main_distance = compare_right;
//                    destination = right;
//                }
//            }
            if (set_right_left == -1) {
                if (same_distance.size() == 0)
                    break;
                else {
                    root_child = same_distance.get(0);
                    same_distance.remove(0);
                }
            }
        }

        // set the best distance color green
        for (StackPane x : DigiController.stackPanes){

            Label label = (Label) x.getChildren().get(1);
            if (Arrays.equals(label.getText().split(" "), destination)){
                Circle circle = (Circle) x.getChildren().get(0);
                circle.setFill(Color.rgb(38, 105, 58));
                circle.setStroke(Color.rgb(58, 112, 168));
            }
        }
    }


    void set_search_color (String[] left, ArrayList<String[]> right){

        for (StackPane stackPane : DigiController.stackPanes){
            set_the_color(left, stackPane);
            if (right != null)
                for (String[] x : right){
                    if (x != null)
                        set_the_color(x, stackPane);
                }
        }
    }

    private void set_the_color(String[] pos, StackPane stackPane) {
        if (pos != null){

            Label label = (Label) stackPane.getChildren().get(1);
            if (Arrays.equals(label.getText().split(" "), pos)){
                Circle circle = (Circle)stackPane.getChildren().get(0);
                circle.setFill(Color.rgb(79, 75, 23));
            }

        }
    }

    public void remove (String val){
        DigiController.textFields.removeIf(x -> x.getText().equals(val));
    }

    // insert a node in BST
    public void insert(String key)  {
        root = insert_Recursive(root, key);
    }

    //recursive insert function
    Node insert_Recursive(Node root, String key) {
        //tree is empty
        if (root == null) {
            root = new Node(key);
            return root;
        }

        return root;
    }
}

