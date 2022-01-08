package com.company.model;

import com.company.controller.DigiController;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Arrays;

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
    //delete a node from BST
//    void deleteKey(int key) {
//        root = delete_Recursive(root, key);
//    }

    public void search (String[] search_val){

        String[] repair_shop = root.key.split(" ");

        double main_distance = Math.pow(Double.parseDouble(search_val[0]) - Double.parseDouble(repair_shop[0]), 2) + Math.pow(Double.parseDouble(search_val[1]) - Double.parseDouble(repair_shop[1]), 2) + Math.pow(Double.parseDouble(search_val[2]) - Double.parseDouble(repair_shop[2]), 2);
        String destination;
        ArrayList<Node> same_distance = new ArrayList<>();
        ArrayList<String[]> nearest = new ArrayList<>();

        Node root_child = root;
        while (true){

            System.out.println(666);
            String[] right = null, left = null;
            double compare_right = -1;
            double compare_left = -1;

            if (root_child.right.key != null) {
                right = root_child.right.key.split(" ");
                compare_right = Math.pow(Double.parseDouble(search_val[0]) - Double.parseDouble(right[0]), 2) + Math.pow(Double.parseDouble(search_val[1]) - Double.parseDouble(right[1]), 2) + Math.pow(Double.parseDouble(search_val[2]) - Double.parseDouble(right[2]), 2);
            }

            if (root_child.left.key != null){
                left = root_child.left.key.split(" ");
                compare_right = Math.pow(Double.parseDouble(search_val[0]) - Double.parseDouble(left[0]), 2) + Math.pow(Double.parseDouble(search_val[1]) - Double.parseDouble(left[1]), 2) + Math.pow(Double.parseDouble(search_val[2]) - Double.parseDouble(left[2]), 2);
            }
            set_search_color(left, right);
            if (compare_left >= 0 && compare_right >= 0){
                if (compare_left < compare_right){
                    root_child = root_child.left;
                    if (compare_left < main_distance)
                        main_distance = compare_left;
                }
                else if (compare_right < compare_left){
                    root_child = root_child.right;
                    if (compare_right < main_distance)
                        main_distance = compare_right;
                }
                if (compare_left == compare_right){

                    if (main_distance > compare_left)
                        main_distance = compare_left;
                    same_distance.add(root_child.right);
                    root_child = root_child.left;
                }
            }
            break;
        }
    }


    void set_search_color (String[] left, String[] right){



        for (StackPane stackPane : DigiController.stackPanes){

            if (left != null){

                Label label = (Label) stackPane.getChildren().get(1);
                if (Arrays.equals(label.getText().split(" "), left)){
                    Circle circle = (Circle)stackPane.getChildren().get(0);
                    circle.setFill(Color.GOLD);
                }

            }
            if (right != null){
                Label label = (Label) stackPane.getChildren().get(1);

                if (Arrays.equals(label.getText().split(" "), right)){
                    Circle circle = (Circle)stackPane.getChildren().get(0);
                    circle.setFill(Color.GOLD);
                }
            }
        }
    }

    //recursive delete function
//    Node delete_Recursive(Node root, int key)  {
//        //tree is empty
//        if (root == null)  return root;
//
//        //traverse the tree
//        if (key < root.key)     //traverse left subtree
//            root.left = delete_Recursive(root.left, key);
//        else if (key > root.key)  //traverse right subtree
//            root.right = delete_Recursive(root.right, key);
//        else  {
//            // node contains only one child
//            if (root.left == null)
//                return root.right;
//            else if (root.right == null)
//                return root.left;
//
//            // node has two children;
//            //get inorder successor (min value in the right subtree)
//            root.key = minValue(root.right);
//
//            // Delete the inorder successor
//            root.right = delete_Recursive(root.right, root.key);
//        }
//        return root;
//    }

//    int minValue(Node root)  {
//        //initially minval = root
//        int minval = root.key;
//        //find minval
//        while (root.left != null)  {
//            minval = root.left.key;
//            root = root.left;
//        }
//        return minval;
//    }

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
        //traverse the tree
//        System.out.println(counter);
//        System.out.println(key);
//        if (counter %2 != 0)     //insert in the left subtree
//            root.left = insert_Recursive(root.left, key, 2);
//        else     //insert in the right subtree
//            root.right = insert_Recursive(root.right, key, 3);
        // return pointer
        return root;
    }

    // method for inorder traversal of BST
    public void inorder() {
        inorder_Recursive(root);
    }

    // recursively traverse the BST
    void inorder_Recursive(Node root) {
        if (root != null) {
            inorder_Recursive(root.left);
            System.out.print(root.key + " ");
            inorder_Recursive(root.right);
        }
    }

//    boolean search(int key)  {
//        root = search_Recursive(root, key);
//        if (root!= null)
//            return true;
//        else
//            return false;
//    }

    //recursive insert function
//    Node search_Recursive(Node root, int key)  {
//        // Base Cases: root is null or key is present at root
//        if (root==null || root.key==key)
//            return root;
//        // val is greater than root's key
//        if (root.key > key)
//            return search_Recursive(root.left, key);
//        // val is less than root's key
//        return search_Recursive(root.right, key);
//    }
}

