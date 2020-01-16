package com.mycompany.mavenproject1;

import static com.mycompany.mavenproject1.Gui.loginPage;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyJavaFX extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws IOException {
        
        loginPage(primaryStage);

    }

    public static void main(String[] args) {
        Application.launch(args);

    }
}

