/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.models;

/**
 *
 * @author nox
 */
public class LoggedAdmin extends LoggedEmployee {

    public LoggedAdmin() {
        super.setAccessLevel("admin");
    }
    public boolean deleteEmployee(int id){
        return true;
    }
    
    public boolean addEmployee(EmployeeBean e){
        return true;
    }
    
    public boolean modifyEmployee(EmployeeBean e){
        return true;
    }
}
