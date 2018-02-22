package me.jack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Main extends Application {

    public static Stage loginMenu;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loginMenu = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        primaryStage.setTitle("Login...");
        primaryStage.setScene(new Scene(root, 774, 536));

        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
