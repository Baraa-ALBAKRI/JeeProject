/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import lsi.m1.models.EmployeeBean;

/**
 *
 * @author mhdba
 */
public interface IDBActions {
    void runQuery(String req, String... parametres);
    ResultSet getResultSet(String req, String... parametres);
    EmployeeBean getEmployee(int id);
    ArrayList getEmployees();
}
