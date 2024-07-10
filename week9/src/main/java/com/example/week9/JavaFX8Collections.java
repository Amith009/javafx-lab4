package com.example.week9;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class JavaFX8Collections extends Application {

    @Override
    public void start(Stage primaryStage) {

        //Nodes
        Text txtNote = new Text("Notification");
        txtNote.setFont(Font.font("Comic Sans MS",25));
        txtNote.setFill(Color.GREEN);

        TextField fldAdd = new TextField();
        fldAdd.setPromptText("Add Field");

        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(85);
//        Button btnAddAll = new Button("Add All");
//        btnAddAll.setMinWidth(85);
       Button btnRemove = new Button("Delete");
       btnRemove.setMinWidth(85);
//        Button btnRemoveAll = new Button("Remove All");
//        btnRemoveAll.setMinWidth(85);
//        Button btnSort = new Button("Sort");
//        btnSort.setMinWidth(85);
//        Button btnReplace = new Button("Replace");
//        btnReplace.setMinWidth(85);
//        Button btnReplaceAll = new Button("Replace All");
//        btnReplaceAll.setMinWidth(85);

        //ListView and Observable List
        ListView<String> listList = new ListView<>();
        ObservableList<String> animals = FXCollections.observableArrayList(
                "Ronaldinho",
                "Zidane",
                "Maldini",
                "Puyol",
                "Maradona");
        listList.setItems(animals);
        listList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listList.setMaxSize(300, 300);

        //ObservableList ChangeListener
        animals.addListener(new ListChangeListener() {

            @Override
            public void onChanged(ListChangeListener.Change change) {

                txtNote.setText("ListView Successfully changed");
            }
        });


        //Button Add
        btnAdd.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {

                String username = fldAdd.getText();
                boolean isUsernameValid = username.matches("^[A-Z][a-zA-Z]{4,}$");

                if(username.isEmpty()){
                    txtNote.setText("Name cannot be empty");
//                    if(fldAdd.getText().contains(",")){
//                        txtNote.setText("Please use AddAll button to add 2+ items");
                    }
                     else if(isUsernameValid&&username.length()>5){
                        animals.add(fldAdd.getText());
                    }else {
                    txtNote.setText("Name should start wth capital letters and should have atleast 5 letters");
                }



                listList.getSelectionModel().clearSelection();


            }

        });

        //Button AddAll
//        btnAddAll.setOnAction(new EventHandler<ActionEvent>(){
//
//            @Override
//            public void handle(ActionEvent t) {
//
//                if(!fldAdd.getText().isEmpty()){
//                    if(fldAdd.getText().contains(",")){
//                        ObservableList temp = FXCollections.observableArrayList(fldAdd.getText().split(","));
//                        animals.addAll(temp);
//                    }else{
//                        txtNote.setText("Please separate information with (,) comma");
//                    }
//                }else{
//                    txtNote.setText("Please Add information to Add field");
//                }
//                listList.getSelectionModel().clearSelection();
//
//            }
//        });

        //Button Remove
        btnRemove.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {

                if(!listList.getSelectionModel().isEmpty()){
                    if(listList.getSelectionModel().getSelectedItems().size() > 1){
                        txtNote.setText("Please use RemoveAll button to remove 2+ items");
                    }else{
                        animals.remove(listList.getSelectionModel().getSelectedItem());
                    }
                }else{
                    txtNote.setText("Select at least 1 Element on the ListView");
                }
                listList.getSelectionModel().clearSelection();
            }
        });

        //Button RemoveAll
//        btnRemoveAll.setOnAction(new EventHandler<ActionEvent>(){
//
//            @Override
//            public void handle(ActionEvent t) {
//                if(!listList.getSelectionModel().isEmpty()){
//                    ObservableList temp = FXCollections.observableArrayList(listList.getSelectionModel().getSelectedItems());
//                    animals.removeAll(temp);
//                }else{
//                    txtNote.setText("Select at least 1 Element on the ListView");
//                }
//                listList.getSelectionModel().clearSelection();
//            }
//        });

        //Button Sort
//        btnSort.setOnAction(new EventHandler<ActionEvent>(){
//
//            @Override
//            public void handle(ActionEvent t) {
//                FXCollections.sort(animals);
//                listList.getSelectionModel().clearSelection();
//            }
//        });
//
//        //Button Replace All
//        btnReplace.setOnAction(new EventHandler<ActionEvent>(){
//
//            @Override
//            public void handle(ActionEvent t) {
//
//                if(listList.getSelectionModel().isEmpty() || fldAdd.getText().isEmpty()){
//                    txtNote.setText("Please select an item and add Information to Add field");
//                }else{
//                    if(!(listList.getSelectionModel().getSelectedItems().size() > 1)){
//                        animals.set(listList.getSelectionModel().getSelectedIndex(), fldAdd.getText());
//                        listList.getSelectionModel().clearSelection();
//                    }else{
//                        txtNote.setText("Use Button Replace All to replace several items");
//                    }
//
//                }
//            }
//        });
//
//        //Button Replace All
//        btnReplaceAll.setOnAction(new EventHandler<ActionEvent>(){
//
//            @Override
//            public void handle(ActionEvent t) {
//                if(listList.getSelectionModel().isEmpty() || fldAdd.getText().isEmpty()){
//                    txtNote.setText("Please select an item and add Information to Add field");
//                }else{
//                    FXCollections.replaceAll(animals, listList.getSelectionModel().getSelectedItem(), fldAdd.getText());
//                    listList.getSelectionModel().clearSelection();
//                }
//            }
//        });


        //Pane Left Right
        VBox right = new VBox(10);
        right.setPadding(new Insets(10));
        right.setAlignment(Pos.CENTER);
        right.getChildren().addAll(fldAdd, btnAdd/*btnAddAll*/, btnRemove/*btnRemoveAll, btnReplace, btnReplaceAll, btnSort*/);

        //Root Node
        BorderPane root = new BorderPane();

        root.setCenter(listList);
        root.setRight(right);
        root.setBottom(txtNote);


        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("JavaFX 8 - FXCollections");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}