package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.MyJavaFX;
import com.mycompany.mavenproject1.User;
import static com.mycompany.mavenproject1.User.configFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.text.View;
import org.omg.CORBA.INTERNAL;

public class Gui extends User {

    static ArrayList<String> objNames = new ArrayList<>();

    public static void loginPage(Stage primaryStage) throws IOException {
        User currUser = new User();
        primaryStage.setTitle("A to-do list ");
        GridPane gridPane = new GridPane();
        //Pane pane =new Pane();
        //Text intro = new Text("This program allow multiple users to add a to-do list ");
        // pane.getChildren().add(intro);
        Text textUsername = new Text("username");
        Text textPassword = new Text("Password");
        Text loginStatue = new Text("");
        TextField userName = new TextField();
        TextField passWord = new TextField();
         
        Button logInBut = new Button("Login");
        
        currentUser = reteriveUsersdata(currentUser);
        
        logInBut.setOnAction((ActionEvent t) -> {
            try {
                System.out.println("After login button pressed");
                
                checkLogin(currentUser, userName, passWord, loginStatue);
                if (loginStatue.getText().equals("logingin")) {

                    userPage(primaryStage, userName);
                 System.out.println("After userPage loaded");
                }
            } catch (IOException ex) {
                System.out.println("Exception while loading users data");
            }
        });

        Button RegBut = new Button("Register");
        RegBut.setOnAction((ActionEvent t) -> {
            registerPage(primaryStage);
        });

        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridPane.setAlignment(Pos.TOP_LEFT);
        // gridPane.add(intro, 0, 0);
        gridPane.add(textUsername, 0, 1);
        gridPane.add(textPassword, 0, 2);
        gridPane.add(userName, 1, 1);
        gridPane.add(passWord, 1, 2);
        gridPane.add(logInBut, 1, 3);
        gridPane.add(loginStatue, 2, 3);
        gridPane.add(RegBut, 1, 4);
        Scene scene = new Scene(gridPane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show(); // Display the stage

    }

    public static void registerPage(Stage primaryStage) {

        primaryStage.setTitle("Create A new account");
        GridPane gridPane = new GridPane();
        Text firstName = new Text("First name");
        Text lastName = new Text("Last name");
        Text userName = new Text("user name");
        Text passWord = new Text("password");

        Text chekedUserame = new Text("user name must be unique");
        Text vailedUserame = new Text("vailed user name");
        Text unvailedUserame = new Text("alerady exist");

        TextField firstnameEntered = new TextField();
        TextField lastnameEntered = new TextField();
        TextField usernameEntered = new TextField();
        TextField passwordEntered = new TextField();

        Button done = new Button("Done");

        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridPane.setAlignment(Pos.TOP_LEFT);

        gridPane.add(firstName, 0, 1);
        gridPane.add(lastName, 0, 2);
        gridPane.add(userName, 0, 3);
        gridPane.add(passWord, 0, 4);

        gridPane.add(firstnameEntered, 1, 1);
        gridPane.add(lastnameEntered, 1, 2);
        gridPane.add(usernameEntered, 1, 3);
        gridPane.add(passwordEntered, 1, 4);

        gridPane.add(chekedUserame, 2, 3);
        gridPane.add(done, 0, 5);

        done.setOnAction((ActionEvent t) -> {

            User e = new User(firstnameEntered.getText(), lastnameEntered.getText(), 
                              usernameEntered.getText(), passwordEntered.getText());
           
          //  currentUser.add(e);
            try {
                System.out.println("berfoe iD = "+getiD());
               // saveUserreg(currentUser.get(getiD() - 1));
                saveUserreg(e,unvailedUserame, gridPane);
                                

            } catch (Exception ex) {
                System.out.println("Excption create new object for registed user");                
            }
        });

        Scene scene = new Scene(gridPane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show(); // Display the stage
    }

    public static void userPage(Stage primaryStage, TextField userName) throws IOException {
        String thisUser = userName.getText();

        String data;
        int noButtons = 0;
        primaryStage.setTitle("Add ,Update ,Search or remove elements from your list ");
        GridPane gridPane = new GridPane();
        Text loginUser = new Text();
        loginUser.setText(thisUser);
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.add(loginUser, 1, 0);

        Button addtaskButton1 = new Button("Add Task 1");
        TextField taskEntered1 = new TextField();
        Button removetaskButton1 = new Button("Remove Task");
        Button updatetaskButton1 = new Button("Update Task");
        Text tasktext1 = new Text("");

        Button addtaskButton2 = new Button("Add Task 2");
        TextField taskEntered2 = new TextField();
        Button removetaskButton2 = new Button("Remove Task 2");
        Button updatetaskButton2 = new Button("Update Task 2");
        Text tasktext2 = new Text("");

        Button addtaskButton3 = new Button("Add Task 3");
        TextField taskEntered3 = new TextField();
        Button removetaskButton3 = new Button("Remove Task3");
        Button updatetaskButton3 = new Button("Update Task3");
        Text tasktext3 = new Text("");

        TextField searchEntered = new TextField();
        Button searchtaskButton = new Button("Search");
        Text searchText = new Text("");

        Button addsaveButton = new Button("Save");
        Text savetext = new Text("");

        int temp = getiD();
        int i = 0;
        int nosavedTasks = 0;
        for (i = 0; i < temp; i++) {
            if (currentUser.get(i).getUserName().equals(userName.getText())) {
                break;
            }
        }

        String fileName = "User" + (i + 1) + "ToDoList.txt";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

       
        

        while ((data = reader.readLine()) != null) {
               System.out.println("data " + data);
            if (!data.isEmpty()) {

                nosavedTasks++;
                if (nosavedTasks == 1) {

                    tasktext1.setText(data);
                    currentUser.get(i).toDolist.add(data);
                    System.out.println("data 1 = " + currentUser.get(i).toDolist.get(nosavedTasks - 1));
                    addtaskButton1.setDisable(true);
                }

                if (nosavedTasks == 2) {

                    tasktext2.setText(data);
                    currentUser.get(i).toDolist.add(data);
                    System.out.println("data 2 = " + currentUser.get(i).toDolist.get(nosavedTasks - 1));
                    addtaskButton2.setDisable(true);
                }

                if (nosavedTasks == 3) {
                    tasktext3.setText(data);
                    currentUser.get(i).toDolist.add(data);
                    addtaskButton3.setDisable(true);
                }

            }
        }

        System.out.println("read first task : " + reader.readLine());

        gridPane.add(taskEntered1, 0, 1);
        gridPane.add(addtaskButton1, 1, 1);
        gridPane.add(removetaskButton1, 2, 1);
        gridPane.add(updatetaskButton1, 3, 1);
        gridPane.add(tasktext1, 4, 1);

        gridPane.add(taskEntered2, 0, 2);
        gridPane.add(addtaskButton2, 1, 2);
        gridPane.add(removetaskButton2, 2, 2);
        gridPane.add(updatetaskButton2, 3, 2);
        gridPane.add(tasktext2, 4, 2);

        gridPane.add(taskEntered3, 0, 3);
        gridPane.add(addtaskButton3, 1, 3);
        gridPane.add(removetaskButton3, 2, 3);
        gridPane.add(updatetaskButton3, 3, 3);
        gridPane.add(tasktext3, 4, 3);

        gridPane.add(searchEntered, 0, 5);
        gridPane.add(searchtaskButton, 1, 5);
        gridPane.add(searchText, 2, 5);

        gridPane.add(addsaveButton, 2, 6);
        gridPane.add(savetext, 2, 7);

        /*
        removetaskButton1.setDisable(true);
        updatetaskButton1.setDisable(true);
        removetaskButton2.setDisable(true);
        updatetaskButton2.setDisable(true);
        removetaskButton3.setDisable(true);
        updatetaskButton3.setDisable(true);
         */
        addtaskButton1.setOnAction((ActionEvent event) -> {
            try {
                addList(userName, taskEntered1, tasktext1, addtaskButton1, removetaskButton1, updatetaskButton1);
                addtaskButton1.setDisable(true);
            } catch (IOException ex) {
                System.out.println("Exception while writing todolist to a file");
            }
        });

        addtaskButton2.setOnAction((ActionEvent event) -> {
            try {
                addList(userName, taskEntered2, tasktext2, addtaskButton2, removetaskButton2, updatetaskButton2);
                addtaskButton1.setDisable(true);
            } catch (IOException ex) {
                System.out.println("Exception while writing todolist to a file");
            }
        });

        addtaskButton3.setOnAction((ActionEvent event) -> {
            try {
                addList(userName, taskEntered3, tasktext3, addtaskButton3, removetaskButton3, updatetaskButton3);
                addtaskButton1.setDisable(true);
            } catch (IOException ex) {
                System.out.println("Exception while writing todolist to a file");
            }
        });

        updatetaskButton1.setOnAction((ActionEvent event) -> {
            try {

                updateList(userName, taskEntered1, tasktext1, 0);
                addtaskButton1.setDisable(true);
            } catch (IOException ex) {
                System.out.println("Exception while updating todolist to a file");
            }
        });

        updatetaskButton2.setOnAction((ActionEvent event) -> {
            try {

                updateList(userName, taskEntered2, tasktext2, 1);
                addtaskButton1.setDisable(true);
            } catch (IOException ex) {
                System.out.println("Exception while updating todolist to a file");
            }
        });

        updatetaskButton3.setOnAction((ActionEvent event) -> {
            try {

                updateList(userName, taskEntered3, tasktext3, 2);
                addtaskButton1.setDisable(true);
            } catch (IOException ex) {
                System.out.println("Exception while updating todolist to a file");
            }
        });

        removetaskButton1.setOnAction((ActionEvent event) -> {
            try {
                removeList(userName, taskEntered1, tasktext1, addtaskButton1, removetaskButton1, 0);
                removetaskButton1.setDisable(true);
            } catch (IOException ex) {
                System.out.println("Exception while writing todolist to a file");
            }
        });

        removetaskButton2.setOnAction((ActionEvent event) -> {
            try {
                removeList(userName, taskEntered2, tasktext2, addtaskButton2, removetaskButton2, 1);
                removetaskButton2.setDisable(true);
            } catch (IOException ex) {
                System.out.println("Exception while removing todolist to a file");
            }
        });

        removetaskButton3.setOnAction((ActionEvent event) -> {
            try {
                removeList(userName, taskEntered3, tasktext3, addtaskButton3, removetaskButton3, 2);
                removetaskButton3.setDisable(true);
            } catch (IOException ex) {
                System.out.println("Exception while REmoving todolist to a file");
            }
        });

       
        searchtaskButton.setOnAction((ActionEvent event) -> {
            try {
                searchList(userName, searchEntered, searchText);

            } catch (IOException ex) {
                System.out.println("Exception while REmoving todolist to a file");
            }
        });
        
        
        
        addsaveButton.setOnAction((ActionEvent event) -> {
            try {
                saveList(userName,savetext);

            } catch (IOException ex) {
                System.out.println("Exception while Saving todolist to a file");
            }
        });
                
                
                
        Scene scene = new Scene(gridPane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show(); // Display the stage

    }

}

/*

     addnewtaskButton.get(size).setOnAction((ActionEvent t) -> {
            try {
           
                        
                System.out.println(" No of buttons  = "+ (addnewtaskButton.size()));
             // Button newaddtaskButton = new Button("Add Task");
             // Button addtaskButton = new Button();
                Button newremovetaskButton = new Button("Remove Task");
                Button newupdatetaskButton = new Button("Update Task");
                TextField newtaskentered = new TextField();
                saveList(userName, taskEntered, tasktext, newtaskentered, addnewtaskButton, addtaskButton, newremovetaskButton, newupdatetaskButton, gridPane);
                System.out.println(" No of buttons after coming back = "+ (addnewtaskButton.size()));
                
                
            } 
            catch (IOException ex) {
                System.out.println("Exception while writing todolist to a file");
            }

        });  
 */
 /*


    public static void userPage(Stage primaryStage, TextField userName) {
        String thisUser = userName.getText();
        ArrayList<Button> addnewtaskButton = new ArrayList<>();
        int noButtons = 0;
        primaryStage.setTitle("Add ,Update ,Search or remove elements from your list ");
        GridPane gridPane = new GridPane();
        Text loginUser = new Text();
        loginUser.setText(thisUser);
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.add(loginUser, 1, 0);

        Button addtaskButton = new Button("Add Task");
        addnewtaskButton.add(addtaskButton);
        Button removetaskButton = new Button("Remove Task");
        Button updatetaskButton = new Button("Update Task");

        Text tasktext = new Text("");

        TextField taskEntered = new TextField();

        gridPane.add(taskEntered, 0, 1);

        gridPane.add(addnewtaskButton.get(noButtons), 1, 1);
        //System.out.println("out no of buttons = "+noButtons);
        gridPane.add(removetaskButton, 2, 1);
        gridPane.add(updatetaskButton, 3, 1);
        gridPane.add(tasktext, 4, 1);
        int size = (addnewtaskButton.size()-1);
        // System.out.println(" No of buttons after coming back whats wrong here= "+ (addnewtaskButton.size()));

   
            addnewtaskButton.get(size).setOnAction((ActionEvent event) -> {
                try {
                    System.out.println(" No of buttons  = " + (addnewtaskButton.size()));
                    // Button newaddtaskButton = new Button("Add Task");
                    // Button addtaskButton = new Button();
                    Button newremovetaskButton = new Button("Remove Task");
                    Button newupdatetaskButton = new Button("Update Task");
                    TextField newtaskentered = new TextField();
                    //saveList(userName, taskEntered, tasktext, newtaskentered, addnewtaskButton, addtaskButton, newremovetaskButton, newupdatetaskButton, gridPane);
                    System.out.println(" No of buttons after coming back = " + (addnewtaskButton.size()));

                } catch (IOException ex) {
                    System.out.println("Exception while writing todolist to a file");
                }
            });
            System.out.println(" out herer = " + (addnewtaskButton.size()));

            Scene scene = new Scene(gridPane, 600, 500);
            primaryStage.setScene(scene);
            primaryStage.show(); // Display the stage

        }
        //}
    }

 */
