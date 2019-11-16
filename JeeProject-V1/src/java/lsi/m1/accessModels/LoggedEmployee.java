/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.accessModels;

import java.util.List;
import lsi.m1.DBmodels.EmployeeBean;
import lsi.m1.data.AppActions;

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
    
    /**Get the list of all the employees.
     * 
     * @param db Session Bean used to interact with DB.
     * @return the list of all the employees in DB.
     */
    public List<EmployeeBean> getEmployeesList(AppActions db){
        return db.getEmployees();
    }
    
    /**Get access level of the logged class
     * 
     * @return the accessLevel
     */
    public String getAccessLevel() {
        return accessLevel;
    }
    
    /**Set the access level by the constructor
     * 
     */
    private void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
