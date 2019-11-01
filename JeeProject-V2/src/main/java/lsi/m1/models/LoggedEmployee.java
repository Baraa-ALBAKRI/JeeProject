/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.models;

import java.util.List;

/**
 *
 * @author nox
 */
public class LoggedEmployee {
    private String accessLevel;
    
    public LoggedEmployee(){
        this.accessLevel = "employee";
    }
    
    public List<EmployeeBean> getEmployeesList(DBActions db){
        return db.getEmployees();
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
