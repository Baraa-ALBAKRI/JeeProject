/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.v2.DBmodels;

import java.util.List;

/**
 *
 * @author mhdba
 */
public interface AppActions {
    /** Delete employee from the DB
     * 
     * @param id the id of the employee
     */
    void deleteEmployee(int id);
    /** Update employee in the DB
     * 
     * @param e the employee to update
     */
    void updateEmployee(Employees e);
    /** Insert employee in the DB
     * 
     * @param e the employee to insert
     */
    void insertEmployee(Employees e);
    /** Get employee from the DB using its id
     * 
     * @param id the id of the employee
     * @return the employee 
     */
    Employees getEmployee(int id);
    /** Get the list of employees from the DB
     * 
     * @return the list from employees
     */
    List getEmployees();
}
