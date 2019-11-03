/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.accessModels;

import lsi.m1.data.DBActions;
import java.util.List;
import lsi.m1.DBmodels.EmployeeBean;

/**
 *
 * @author nox
 */
public class LoggedEmployee {
    private String accessLevel;
    
    public LoggedEmployee(){
        this("employee");
    }
    
    protected LoggedEmployee(String accessLevel){
        setAccessLevel(accessLevel);
    }
    
    public List<EmployeeBean> getEmployeesList(DBActions db){
        return db.getEmployees();
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    private void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
