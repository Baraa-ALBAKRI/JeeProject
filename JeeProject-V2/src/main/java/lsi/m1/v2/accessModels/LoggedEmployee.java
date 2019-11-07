/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.v2.accessModels;

import java.util.List;
import lsi.m1.v2.DBmodels.EmployeeSB;
import lsi.m1.v2.DBmodels.Employees;

/**
 *
 * @author mhdba
 */
public class LoggedEmployee {
    private String accessLevel;
    
    public LoggedEmployee(){
        this("employee");
    }
    
    protected LoggedEmployee(String accessLevel){
        setAccessLevel(accessLevel);
    }
    
    public List<Employees> getEmployeesList(EmployeeSB db){
        return db.getEmployees();
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    private void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
