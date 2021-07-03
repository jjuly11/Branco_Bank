/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brankobank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author 
 */
public class model {
    public String admin;
    public String password;
    public static JSONObject user;
    public static JSONObject employee;
    private static JSONArray modUserList;
    private static JSONArray modEmployeeList;
    private static String adminPassword="admin";
    private static int dbDepAmt;
    private static int dbWithAmt;
    private static boolean depRanOnce=false;
    private static boolean withRanOnce=false;
    
    
//    int t;
    
    public static boolean check_pin(String pin, JSONArray userList){
//        System.out.println(user);
        String dbPin = (String) user.get("pin");
        
        if(dbPin.equals(pin)){
                System.out.println("Pin was correct");
                return true;
            }
        else{
            System.out.println("Pin was incorrect");
        }
         
        return false;
        
    }
    
//    Utility Class Used to Print information in the console.
    
    public static void printIt(Object thing){
        System.out.println(thing);
    }
//    JSON For Database readings
    
    public static boolean userValidation(JSONArray userList, String username, String passcode){
//        System.out.println(userList); 
        
//        userList.forEach( user -> parseUserObject( (JSONObject) user ) );
        
        modUserList = userList;
        for(int i=0; i < userList.size(); i++){
            JSONObject userObject =(JSONObject) userList.get(i);

            JSONObject dbUser = (JSONObject) userObject.get("user");


            String dbName = (String) dbUser.get("username");

            String dbPassword = (String) dbUser.get("password");
            
//            System.out.println("Inside the userValidation"+username);
            if(dbName.equals(username)){

                if(dbPassword.equals(passcode)){
                    
                    user = (JSONObject) userObject.get("user");
//                    printIt(dbName+" Has access");
                    
                    modUserList.remove(i);
                    
                    
                    return true;
                }

            }

        }
        
        
//          for(JSONObject user : userList ){
//              
//          }
        return false;
    }
    
    public static boolean adminValidation(JSONArray adminList, String username, String passcode){
        if(username.equals("admin")){
            
            for(int i=0; i < adminList.size(); i++){
                JSONObject adminObject =(JSONObject) adminList.get(i);
            
                JSONObject dbUser = (JSONObject) adminObject.get("admin");

                String dbPassword = (String) dbUser.get("password");

                if(dbPassword.equals(adminPassword)){
                    printIt(username+" Is the Administrator");
                    return true;
                }
                else{
                    printIt("Incorrect password");
                    return false;
                }
            }
            
        }
        return false;
    }
//    private parseUserObject
    
//    ATM Functions
    
    public static int deposit(int amount, String acc){
        System.out.println(acc);
        if(!depRanOnce){
            dbDepAmt= Integer.parseInt((String)user.get(acc));
            depRanOnce=true;
        }
     
        if(amount > 0){
            dbDepAmt = dbDepAmt+amount;
            user.put(acc, Integer.toString(dbDepAmt));
        }
        else{
            System.out.println("Too little money");
        }
        System.out.println(dbDepAmt);
        return dbDepAmt;
    }
    
    public static int withdraw(int amount, String acc){
//        System.out.println(acc);
        if(!withRanOnce){
            dbWithAmt= Integer.parseInt((String)user.get(acc));
            withRanOnce=true;
        }
     
        if(amount <= dbWithAmt){
            dbWithAmt = dbWithAmt-amount;
            user.put(acc, Integer.toString(dbWithAmt));
        }
        else{
//            theView1.displayErrorMessage("Error: Trying to withdraw more than is there");
            System.out.println("ERROR Too Much money");
            System.err.print("Error: Trying to withdraw more than is there");
        }
        System.out.println(dbWithAmt);
        return dbWithAmt;
    }
    
    public static int checkb(){

        return Integer.parseInt((String)user.get("checking"));
       
    
    }
    
     public static int checksav(){

        return Integer.parseInt((String)user.get("savings"));

    }
    
    
//    1 -> user
//     2 -> employee
    
    public JSONArray submitChanges(){
        JSONObject completeUser = new JSONObject();
        completeUser.put("user",user);

        modUserList.add(completeUser);
        return modUserList;

        
//        System.out.println(modUserList);
    }
    
//    Administration Section
    public static JSONObject searchedUser(JSONArray userList, String username){
        modEmployeeList = userList;
        for(int i=0; i < userList.size(); i++){
            JSONObject userObject =(JSONObject) userList.get(i);

            JSONObject dbUser = (JSONObject) userObject.get("user");


            String dbName = (String) dbUser.get("username");


//            System.out.println("Inside the userValidation"+username);
            if(dbName.equals(username)){

                user = (JSONObject) userObject.get("user");
//                    printIt(dbName+" Has access");

                modUserList.remove(i);

                break;
            }

    //        return null;
            
        }
        return user;
    }

//    1 -> user
//     2 -> employee

    public JSONArray submitAdminChanges(JSONObject edited_user, String acc, int val){
        JSONObject completeUser = new JSONObject();
        completeUser.put(acc,edited_user);

        if(val == 1){
            modUserList.add(completeUser);
            return modUserList;
        }
        else{
            modEmployeeList.add(completeUser);
            return modEmployeeList;
        }

//        System.out.println(modUserList);
//        return modUserList;
    }
    public static JSONObject searchedEmployee(JSONArray userList, String username){
        
         modEmployeeList = userList;
        for(int i=0; i < userList.size(); i++){
            JSONObject userObject =(JSONObject) userList.get(i);

            JSONObject dbUser = (JSONObject) userObject.get("employee");


            String dbName = (String) dbUser.get("username");


//            System.out.println(dbName);
            if(dbName.equals(username)){

                employee = (JSONObject) userObject.get("employee");
//                    printIt(dbName+" Has access");

                modEmployeeList.remove(i);

                break;
            }

    //        return null;
            
        }
        return employee;
    }
}
