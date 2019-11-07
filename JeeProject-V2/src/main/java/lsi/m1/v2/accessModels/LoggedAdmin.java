/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.v2.accessModels;

import lsi.m1.v2.DBmodels.EmployeeSB;
import lsi.m1.v2.DBmodels.Employees;

/**
 *
 * @author mhdba
 */
public class LoggedAdmin extends LoggedEmployee {

    public LoggedAdmin() {
        super("admin");
    }
    
    public Employees getEmployee(int id, EmployeeSB db){
        return db.getEmployee(id);
    }
}
