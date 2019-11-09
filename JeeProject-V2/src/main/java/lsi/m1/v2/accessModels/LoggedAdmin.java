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
    
    public Employees getEmployee(int id, EmployeeSB employeeSB){
        return employeeSB.getEmployee(id);
    }
    
    public void deleteEmployee(int id, EmployeeSB employeeSB) {
        employeeSB.deleteEmployee(id);
    }
    
    public void modifyEmployee(Employees e, EmployeeSB employeeSB) {
        employeeSB.modifyEmployee(e);
    }
    
    public void addEmployee(Employees e, EmployeeSB employeeSB) {
        employeeSB.insertEmployee(e);
    }
}
