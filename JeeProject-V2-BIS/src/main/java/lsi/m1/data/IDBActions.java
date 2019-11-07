/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.data;

import java.util.ArrayList;
import lsi.m1.DBmodels.EmployeeBean;

/**
 *
 * @author mhdba
 */
public interface IDBActions {
    void deleteEmployee(int id);
    void updateEmployee(EmployeeBean e);
    void insertEmployee(EmployeeBean e);
    EmployeeBean getEmployee(int id);
    ArrayList getEmployees();
}
