package com.mycompany.brankobank;

import java.io.IOException;
import javafx.fxml.FXML;
import com.mycompany.json.jsonObj;
import javafx.scene.control.PasswordField;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javafx.scene.control.TextField;

public class PrimaryController {
    private App theView;
    private model theModel;
    private jsonObj JSONObj;
    
    public TextField username;
    public PasswordField password;
    
    @FXML
    private void login() throws IOException {
        System.out.println(username.getText());
        boolean signInStatus= theModel.userValidation(JSONObj.readJSON("users.json"), username.getText(), password.getText());
        boolean adminValid = theModel.adminValidation(JSONObj.readJSON("admin.json"), username.getText(), password.getText());

        System.out.println(signInStatus);
        if(signInStatus){
            App.setRoot("user_pin");
        }
        else if(adminValid){
            App.setRoot("branch_selection");
        }


    }

    @FXML
    private void userPinEntry() throws IOException{
        App.setRoot("branch_selection");
    }

    @FXML
    private void ManageUserClick() throws IOException{
        System.out.println("Button Clicked");
        App.setRoot("search_user");
    }

    @FXML
    private void MainBranchClick() throws IOException{
        App.setRoot("admin_options");
        System.out.println("Main Branch Button Clicked");
    }

    @FXML
    private void ManageEmployeeClick() throws IOException{
        App.setRoot("search_employee");
        System.out.println("Main Branch Button Clicked");
    }
}
