package com.mycompany.brankobank;

import java.io.IOException;

import com.mycompany.json.jsonObj;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//This controller is for the administration section of the program, top manage the users and employees.


public class SecondaryController {
    private model TheModel = new model();
    private jsonObj JSONObj = new jsonObj();

    public TextArea user_field;
    public TextArea employee_field;

    public TextArea add_user_field;
    public TextArea add_employee_field;

    public TextField searched_user_field;
    public TextField searched_employee_field;

    public JSONObject user;
    public JSONObject employee;
//    private static String name;
    private static String editable_user;

    @FXML
    private void search_users_click() throws IOException {
//        SecondaryController obj = new SecondaryController();
//        name = searched_user_field.getText();
//        This Method Carries out a search across the entire users table in the database using the user's name as the key and if a match is found it will be returned else null.
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
        System.out.println(searched_employee_field.getText());
        employee = TheModel.searchedEmployee(JSONObj.readJSON("employee.json"), searched_employee_field.getText());
        System.out.println(employee);
        if(!(employee == null) ){
            editable_user = employee.toJSONString();
            System.out.println(editable_user);
            App.setRoot("admin_employee_edit");
        }
        else{
            System.out.println("User Not Found");
        }
    }

    @FXML
    private void add_user_click() throws IOException {
        App.setRoot("admin_add_user");
    }

    @FXML
    private void add_employee_click() throws IOException {
        App.setRoot("admin_add_employee");
    }
//    To Remove a user the admin must type the username into the search bar the click remove user.
    @FXML
    private void remove_user_click() throws IOException {
        App.setRoot("admin_remove_user");
    }

    @FXML
    private void remove_employee_click() throws IOException {
        App.setRoot("admin_remove_employee");
    }

    @FXML
    private void load_user_template_click() throws IOException {
        editable_user=(JSONObj.readJSONTemplate("user_template.json")).toJSONString();
        add_user_field.setText(editable_user);
    }

    @FXML
    private void load_employee_template_click() throws IOException {
        editable_user=(JSONObj.readJSONTemplate("employee_template.json")).toJSONString();
        add_employee_field.setText(editable_user);
    }

    @FXML
    private void save_new_user_data_click() throws IOException, ParseException {
        //        Converting the JSON String back into a JSONObject.
        String s = (add_user_field.getText());
        JSONParser parser = new JSONParser();
        JSONObject edited_user=(JSONObject) parser.parse(s);
//        This variable holds the entire data for the users that needs tobe written out to a file.
//        acc can be either user or  employee
//        val -> 1 represents the user.json file and any other number represents the the employee.json file.
        JSONArray arr = TheModel.submitAdminChangesTemplate(edited_user, JSONObj.readJSON("users.json"),"user",1);
        JSONObj.writeJSON(arr,"users.json");
    }

    @FXML
    private void save_new_employee_data_click() throws IOException, ParseException {
        //        Converting the JSON String back into a JSONObject.
        String s = (add_employee_field.getText());
        JSONParser parser = new JSONParser();
        JSONObject edited_user=(JSONObject) parser.parse(s);
//        This variable holds the entire data for the users that needs tobe written out to a file.
//        acc can be either user or  employee
//        val -> 1 represents the user.json file and any other number represents the the employee.json file.
        JSONArray arr = TheModel.submitAdminChangesTemplate(edited_user, JSONObj.readJSON("employee.json"),"employee",2);
        JSONObj.writeJSON(arr,"employee.json");
    }

    @FXML
    private void load_user_data_click() throws IOException {
//        This method outputs the results from a successful query to a textarea on in the currently visible window.
        System.out.println(editable_user);
//        System.out.println(name);
        user_field.setText(editable_user);
    }

    @FXML
    private void load_employee_data_click() throws IOException {
        employee_field.setText(editable_user);
    }

    @FXML
    private void save_user_data_click() throws IOException, ParseException {
//        System.out.println;
//        Converting the JSON String back into a JSONObject.
        String s = (user_field.getText());
        JSONParser parser = new JSONParser();
        JSONObject edited_user=(JSONObject) parser.parse(s);
//        This variable holds the entire data for the users that needs tobe written out to a file.
//        acc can be either user or  employee
//        val -> 1 represents the user.json file and any other number represents the the employee.json file.
        JSONArray arr = TheModel.submitAdminChanges(edited_user,"user",1);
        JSONObj.writeJSON(arr,"users.json");
    }

    @FXML
    private void save_employee_data_click() throws IOException, ParseException {
        String s = (employee_field.getText());
        JSONParser parser = new JSONParser();
        JSONObject edited_user=(JSONObject) parser.parse(s);
//        This variable holds the entire data for the users that needs tobe written out to a file.
        JSONArray arr = TheModel.submitAdminChanges(edited_user, "employee", 2);
        JSONObj.writeJSON(arr,"employee.json");
    }

}