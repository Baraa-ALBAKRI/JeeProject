/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.accessModels;

import lsi.m1.data.DBActions;
import lsi.m1.DBmodels.EmployeeBean;
import static lsi.m1.utils.Constants.*;
/**
 *
 * @author nox
 */
public class LoggedAdmin extends LoggedEmployee {

    public LoggedAdmin() {
        super("admin");
    }

    public void deleteEmployee(int id, DBActions db) {
        db.runQuery(Query_DELETE_ONE_EMPLOYEE, Integer.toString(id));
    }
    
    public EmployeeBean getEmployee(int id, DBActions db){
        return db.getEmployee(id);
    }

    public void addEmployee(EmployeeBean e, DBActions db) {
        db.runQuery(Query_ADD_ONE_EMPLOYEE, e.getLastName(), e.getFirstName(), e.getHomePhone(), e.getMobilePhone(), e.getWorkPhone(), e.getAddress(), e.getZipCode(), e.getCity(), e.getMail());
    }

    public void modifyEmployee(EmployeeBean e, DBActions db) {
        db.runQuery(Query_UPDATE_ONE_EMPLOYEE, e.getLastName(), e.getFirstName(), e.getHomePhone(), e.getMobilePhone(), e.getWorkPhone(), e.getAddress(), e.getZipCode(), e.getCity(), e.getMail(), Integer.toString(e.getId()));
    }
}
