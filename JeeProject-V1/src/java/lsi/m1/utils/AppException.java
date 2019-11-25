/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.utils;

import java.text.SimpleDateFormat;

/**
 *Show the error messages with specific format
 * @author mhdba
 */
public class AppException extends Exception{
    
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static void showMessageError(String message){
        System.err.println(formatter.format(System.currentTimeMillis())
                        + "Error : " + message);
    }
    
}
