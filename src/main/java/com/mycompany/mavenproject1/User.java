package com.mycompany.mavenproject1;

import static com.mycompany.mavenproject1.Gui.userPage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;

public class User {

    private String fName;
    private String lName;
    private String userName;
    private String passwordString;
    private int noTasks = 0;
    private static int iD = 0;
    static ArrayList<User> currentUser = new ArrayList<>();
    static String configFile = "config" + ".txt";
    ArrayList<String> toDolist = new ArrayList<>();

    public User() {
        this.iD++;
    }

    public User(String fName, String lName, String userName, String passwordString) {
        this.fName = fName;
        this.lName = lName;
        this.userName = userName;
        this.passwordString = passwordString;
        this.iD++;

    }

    public int getNoTasks() {
        return noTasks;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordString() {
        return passwordString;
    }

    public ArrayList<String> getToDolist() {
        return toDolist;
    }

    public static int getiD() {
        return iD;
    }

    public void setNoTasks(int noTasks) {
        this.noTasks = noTasks;
    }

    public void incnoTasks() {
        this.noTasks++;
    }

    public static void deiD() {
        iD--;
    }

    public static void iniD() {
        iD++;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPasswordString(String passwordString) {
        this.passwordString = passwordString;
    }

    public void setToDolist(String task) {
        toDolist.add(task);
    }

    public static void saveUserreg(User e, Text unvailedUserame, GridPane gridPane) throws IOException, Exception {
        System.out.println("here");
        System.out.println("iD = " + iD);
        deiD();

        for (int i = 0; i < iD; i++) {

            if (currentUser.get(i).getUserName().equals(e.getUserName())) {
                System.out.println("already exists");
                gridPane.add(unvailedUserame, 0, 6);
                e = null;
                System.out.println("iD = " + iD);
                return;
            }
        }
        iniD();
        System.out.println("new one");
        String fileName = "User" + iD + ".txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        // changing the file permissions 
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(false);
        
        String datafileName = "User" + iD + "ToDoList.txt";
        File datafile = new File(datafileName);
        FileWriter datafileWriter = new FileWriter(datafile);
        // changing the file permissions 
        datafile.setExecutable(true);
        datafile.setReadable(true);
        datafile.setWritable(false);
        datafileWriter.write("");
        
        
        System.out.println("here22222");

        fileWriter.write(e.getfName());
        fileWriter.write("\n");
        fileWriter.flush();

        fileWriter.write(e.getlName());
        fileWriter.write("\n");
        fileWriter.flush();

        fileWriter.write(e.getUserName());
        fileWriter.write("\n");
        fileWriter.flush();

        fileWriter.write(String.valueOf(getiD()));
        fileWriter.write("\n");
        fileWriter.flush();

        fileWriter.write(e.getPasswordString());
        fileWriter.write("\n");
        fileWriter.flush();

         
        File file1 = new File(configFile);
        // changing the file permissions 
        file1.setExecutable(true); 
        file1.setReadable(true); 
        file1.setWritable(false);
        
        
        FileWriter cofigFilewriter= new FileWriter(file1);

        cofigFilewriter.write(String.valueOf(getiD()));
        cofigFilewriter.flush();
        System.out.println(getiD());
         
    }

    public static ArrayList<User> reteriveUsersdata(ArrayList<User> e) throws FileNotFoundException, IOException {

        BufferedReader reader;
        FileReader fileReader = new FileReader(configFile);
        //String fileName;
        iD = fileReader.read();
        iD = iD - 48;
        String data;
        int temp = iD;
        iD = iD - temp;
        System.out.println(iD + "\n");

        for (int i = 1; i <= temp; i++) {
            User user = new User();
            String fileName1 = "User" + i + ".txt";
            reader = new BufferedReader(new FileReader(fileName1));

            data = reader.readLine();
            // e.add(user);
            user.setfName(data);

            data = reader.readLine();
            user.setlName(data);

            data = reader.readLine();
            user.setUserName(data);

            data = reader.readLine();

            data = reader.readLine();
            user.setPasswordString(data);

            e.add(user);

            System.out.println(i + " user added");
        }
        System.out.println("   " + iD);

        return e;
    }

    public static void checkLogin(ArrayList<User> e, TextField username, TextField pass, Text loginStatue) {
        int flag = 0;
        for (int i = 0; i < iD; i++) {
            if (e.get(i).userName.equals(username.getText())) {
                if (e.get(i).passwordString.equals(pass.getText())) {
                    System.out.println("user " + (i + 1) + " logged in");
                    loginStatue.setText("logingin");
                    flag = 1;

                }

            }

        }
        if (flag == 0) {
            System.out.println(" wrong username or password");
            loginStatue.setText("Wrong username or password");

        }

    }

    public static void addList(TextField userName, TextField taskEntered, Text newtaskEntered,
            Button newaddtaskButton, Button removetaskButton, Button updatetaskButton) throws IOException {

        int temp = getiD();
        int i = 0;

        for (i = 0; i < temp; i++) {
            if (currentUser.get(i).getUserName().equals(userName.getText())) {
                break;
            }
        }

        //removetaskButton.setDisable(false);
        //updatetaskButton.setDisable(false);
        currentUser.get(i).toDolist.add(taskEntered.getText());
        /*
        String fileName = "User" + (i + 1) + "ToDoList.txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file, true);
        // changing the file permissions 
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(false);
         */
        System.out.println("writing data to user no = " + (i + 1));
        //fileWriter.write(taskEntered.getText());
        newtaskEntered.setText(taskEntered.getText());
        //fileWriter.write("\n");
        //fileWriter.flush();

        newaddtaskButton.setDisable(true);
        removetaskButton.setDisable(false);
        currentUser.get(i).incnoTasks();

    }

    public static void updateList(TextField userName, TextField taskEntered, Text newtaskEntered,
            int noButton) throws IOException {

        int temp = getiD();
        int i = 0;

        for (i = 0; i < temp; i++) {
            if (currentUser.get(i).getUserName().equals(userName.getText())) {
                break;
            }
        }

        currentUser.get(i).toDolist.remove(noButton);
        currentUser.get(i).toDolist.add(noButton, taskEntered.getText());
        newtaskEntered.setText(currentUser.get(i).toDolist.get(noButton));

        /* String fileName = "User" + (i + 1) + ".txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String newtask;
        
        newtask = reader.readLine();

        while ((newtask = reader.readLine()) != null) {
               
            if (newtaskEntered.getText().equals(newtask)) {
                   
        
        
        fileWriter.write(taskEntered.getText());
        newtaskEntered.setText(taskEntered.getText());
        fileWriter.write("\n");
        fileWriter.flush();
        
        
        
    }
        }*/
    }

    public static void removeList(TextField userName, TextField taskEntered, Text newtaskEntered,
            Button newaddtaskButton, Button removetaskButton, int noButton) throws IOException {
        int temp = getiD();
        int i = 0;

        for (i = 0; i < temp; i++) {
            if (currentUser.get(i).getUserName().equals(userName.getText())) {
                break;
            }
        }
        currentUser.get(i).toDolist.remove(noButton);
        newtaskEntered.setText("");
        newaddtaskButton.setDisable(false);
        removetaskButton.setDisable(true);
    }

    public static void searchList(TextField userName, TextField searchEntered, Text searchText) throws IOException {
        int flag = 0;
        int temp = getiD();
        int i = 0;
        for (i = 0; i < temp; i++) {
            if (currentUser.get(i).getUserName().equals(userName.getText())) {
                break;
            }
        }

        for (int j = 0; j < temp; j++) {
            if (currentUser.get(i).toDolist.get(j).equals(searchEntered.getText())) {
                searchText.setText("Task Found, Task NO " + (j + 1));
                flag = 1;
            }
        }
        if (flag == 0) {
            searchText.setText("Task Not Found");
        }

    }

    public static void saveList(TextField userName, Text newtaskEntered) throws IOException {
        
        System.out.println("saveFile1");
        
        int flag = 0;
        int temp = getiD();
        int i = 0;
        for (i = 0; i < temp; i++) {
            if (currentUser.get(i).getUserName().equals(userName.getText())) {
                break;
            }
        }

        System.out.println("saveFile2");
        
        String fileName = "User" + (i + 1) + "ToDoList.txt";
        
        File file = new File(fileName);
        System.out.println("saveFile3");
        FileWriter fileWriter = new FileWriter(file, false);
        System.out.println("saveFile4");
// changing the file permissions 
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(false);
            
        for (int j = 0; j < currentUser.get(i).toDolist.size(); j++) {
            fileWriter.write(currentUser.get(i).toDolist.get(j));
            fileWriter.write("\n");
            fileWriter.flush();
        }
        newtaskEntered.setText("Data Saved");
    }

}

/*

public static void saveList(TextField userName, TextField taskEntered, Text newtaskEntered,
            Button newaddtaskButton, Button removetaskButton, Button updatetaskButton,
            GridPane gridPane) throws IOException {

        int temp = getiD();
        int i = 0;
        int noButtons;
        for (i = 0; i < temp; i++) {
            if (currentUser.get(i).getUserName().equals(userName.getText())) {
                break;
            }
        }
        String fileName = "User" + (i + 1) + ".txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file, true);
        // changing the file permissions 
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(false);

        System.out.println("writing data to user no = " + (i + 1));
        fileWriter.write(taskEntered.getText());
        fileWriter.write("\n");
        fileWriter.flush();

        currentUser.get(i).incnoTasks();

        //addnewtaskButton.get(noButtons-1).setDisable(true);
        Button addtaskButton = new Button("Add Task " + (currentUser.get(i).getNoTasks() + 1));
        addnewtaskButton.add(addtaskButton);

        System.out.println("noButtons = " + noButtons + "   = " + (currentUser.get(i).getNoTasks() + 1));

        gridPane.add(addnewtaskButton.get(noButtons), 1, (currentUser.get(i).getNoTasks() + 1));
        //gridPane.add(addnewtaskButton.get(noButtons), 1,2);
        //gridPane.add(addnewtaskButton.get(noButtons), 1, 1);

        System.out.println(" size in function = " + addnewtaskButton.size());

        // gridPane.add(removetaskButton, 2, (currentUser.get(i).getNoTasks()+1));
        //gridPane.add(updatetaskButton, 3, (currentUser.get(i).getNoTasks()+1));
        //gridPane.add(newtaskEntered,0,(currentUser.get(i).getNoTasks()+1));
    }

}

 */
