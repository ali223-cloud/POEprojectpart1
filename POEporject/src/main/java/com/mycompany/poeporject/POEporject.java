/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poeporject;

import java.util.Scanner; 
public class POEporject {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
         login login =new login();
        //declaring 
        String username; 
        String password;
        String phonenumber;
        String loginuser;
        String loginpass;
        //user input 
        
        System.out.println("enter username:  ");
        username  =  input.nextLine();
        
        System.out.println("enter password:  ");
        password =  input.nextLine();
        
        System.out.println("enter phonenumber :  ");
        phonenumber  =  input.nextLine();
       
        //loops 
        if(login.cheakusername(username)){
            System.out.println("username successfully found.");
        }else{
            System.out.println("username is not in its correct formatt;"
                    + "please make sure you entered in the correct username formatt with at least 5 letters"
                    + "a capital letter, a number, and a speacial charater");
        }
        
        if(login.cheakpassword(password)){
            System.out.println("password succecful. ");
        }else{
            System.out.println("password is not correct;"
                    + "please ensure that you haveat least 8 charaters,"
                    + "a capital letter, a number, and a special charaters");
            
        }
        if(login.cheakphonenumber(phonenumber)){
            System.out.println(" phone number succesfully entered");
        } else{
            System.out.println("phone number is incorrect;"
                    + "please make sure you follow the correct format"
                    + "or does not contain any international codes");
    }
     System.out.println("    login   ");
     System.out.println("enter your username:  ");
     loginuser = input.nextLine();
     
     System.out.println("enter your password:  ");
     loginpass = input.nextLine();
     
     if(login.loginUser(username, password, loginuser, loginpass)){
     System.out.println("welcome" + username + "happy to see you again");}
     else{
     System.out.println("username or password is incorrect please try agin");
     }
     
        
        
                
    }
}
