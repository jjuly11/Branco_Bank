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

    public PasswordField pin_code;
    
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
        System.out.println(pin_code.getText());
        boolean pinStatus = theModel.check_pin(pin_code.getText(), JSONObj.readJSON("users.json"));
        System.out.println(pinStatus);
        if(pinStatus){
            App.setRoot("user_dashboard");
        }

    }

    @FXML
    private void withdraw_saving_click() throws IOException {
        App.setRoot("saving_withdraw");
    }
    @FXML
    private void withdraw_chequing_click() throws IOException {
        App.setRoot("chequing_withdraw");
    }
    @FXML
    private void deposit_saving_click() throws IOException {
        App.setRoot("saving_deposit");
    }
    @FXML
    private void deposit_chequing_click() throws IOException {
        App.setRoot("chequing_deposit");
    }
    @FXML
    private void saving_balance_click() throws IOException {
        App.setRoot("chequing_deposit");
    }

    @FXML
    private void chequing_balance_click() throws IOException {

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
