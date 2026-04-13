/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poeporject;

/**
 *
 * @author Ali2h
 */
class login {
    // condition 
    private login login =new login();
    public static boolean checkusername(String username){
        return username.contains("_")&& username.length()<=5;
    }

    public static boolean cheakusername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public static boolean cheakpassword(String password) {
        String pattern = "(?=.*[A-Z])"
                       +"(?=.*//d)"
                       +"(?=.*[^a-z0-9]"
                       +".{8,}";// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return password.matches(pattern);
    }

    public static boolean cheakphonenumber(String phonenumber) {
        String pattern = "^//+27[0-9]{9}";
        return phonenumber.matches(pattern);// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static boolean loginUser(String storedusername, String storedpassword, String loginuser, String loginpass) {
       return loginuser.equals(storedusername) && loginpass.equals(storedpassword); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * @return the login
     */
    public login getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(login login) {
        this.login = login;
    }
    
}
