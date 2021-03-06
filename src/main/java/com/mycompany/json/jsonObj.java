/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.json;

import com.mycompany.brankobank.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author
 */
public class jsonObj {
    
    private static model themodel = new model();
    
    public static JSONArray readJSON(String filename){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        
//        JSONArray employeeList;
//            JSONArray userList ;        
        try (FileReader reader = new FileReader(filename))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray userList = (JSONArray) obj;
            return userList;
            
//            themodel.validation(userList, themodel.admin, themodel.password);
//            return employeeList;
           
//            System.out.println(employeeList);
//             
//            //Iterate over employee array
//            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        return [{"empty":"empty"}];
        return null;
    }

    //    This Methods reads the template file and returns a JSONObject
    public static JSONObject readJSONTemplate(String filename){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

//        JSONArray employeeList;
//            JSONArray userList ;
        try (FileReader reader = new FileReader(filename))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONObject List = (JSONObject) obj;
            return List;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        return [{"empty":"empty"}];
        return null;
    }
    
    
    private static void parseEmployeeObject(JSONObject employee) 
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");
         
        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");    
        System.out.println(firstName);
         
        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");  
        System.out.println(lastName);
         
        //Get employee website name
        String website = (String) employeeObject.get("website");    
        System.out.println(website);
    }
    
    public static void createJSON(String user, String password){
        JSONObject userDetails = new JSONObject();
        userDetails.put("username", user);
        userDetails.put("password", password);
        
        
        JSONObject userObject = new JSONObject(); 
        userObject.put("user", userDetails);
        
        JSONArray userList = new JSONArray();
        userList.add(userObject);
        
        try (FileWriter file = new FileWriter("users.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(userList.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeJSON(JSONArray list, String path){
//        System.out.println("in function");
//        File users= new File("users.json");
//        users.delete();
        
        try (FileWriter f = new FileWriter(path,false)) {
            //We can write any JSONArray or JSONObject instance to the file
            System.out.println(list);
            f.write(list.toJSONString()); 
            f.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
             System.out.println(list);
        }
    }
}
