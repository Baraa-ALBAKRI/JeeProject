/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.accessModels;

import lsi.m1.data.DBActions;
import lsi.m1.DBmodels.EmployeeBean;

/**
 *
 * @author nox
 */
public class LoggedAdmin extends LoggedEmployee {

    public LoggedAdmin() {
        super("admin");
    }

    public void deleteEmployee(int id, DBActions db) {
        db.deleteEmployee(id);
    }
    
    public EmployeeBean getEmployee(int id, DBActions db){
        return db.getEmployee(id);
    }

    public void addEmployee(EmployeeBean e, DBActions db) {
        db.insertEmployee(e);
    }

    public void modifyEmployee(EmployeeBean e, DBActions db) {
        db.updateEmployee(e);
    }
}
