package com.mycompany.brankobank;

import java.io.IOException;

import com.mycompany.json.jsonObj;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class SecondaryController {
    private model TheModel = new model();
    private jsonObj JSONObj = new jsonObj();

    public TextArea user_field;
    public TextArea employee_field;

    public TextField searched_user_field;
    public TextField searched_employee_field;

    public JSONObject user;
//    private static String name;
    private static String editable_user;

    @FXML
    private void search_users_click() throws IOException {
        SecondaryController obj = new SecondaryController();
//        name = searched_user_field.getText();
        user = TheModel.searchedUser(JSONObj.readJSON("users.json"), searched_user_field.getText());
        if(!(user == null) ){
            editable_user = user.toJSONString();
            System.out.println(editable_user);
            App.setRoot("admin_user_edit");
        }
        else{
            System.out.println("User Not Found");
        }
//        System.out.println(user);
    }

    @FXML
    private void search_employees_click() throws IOException {

    }

    @FXML
    private void switchToPrimary() throws IOException {

    }

    @FXML
    private void load_user_data_click() throws IOException {
        System.out.println(editable_user);
//        System.out.println(name);
        user_field.setText(editable_user);
    }

    @FXML
    private void load_employee_data_click() throws IOException {

    }

    @FXML
    private void save_user_data_click() throws IOException {
        System.out.println(user_field.getText());
    }

    @FXML
    private void save_employee_data_click() throws IOException {

    }

}