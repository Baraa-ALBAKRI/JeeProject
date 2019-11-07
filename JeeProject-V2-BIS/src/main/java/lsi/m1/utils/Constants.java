/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.utils;

/**
 *
 * @author nox
 */
public class Constants {
    public static final String URL = "jdbc:derby://localhost:1527/JEEPRJ";
    public static final String USER_BDD = "jee";
    public static final String MDP_BDD = "jee";
    public static final String FRM_LOGIN = "loginForm";
    public static final String FRM_PASSWORD = "passwordForm";
    public static final String Query_SELECT_ALL_EMPLOYEES = "SELECT * from EMPLOYEES";
    public static final String Query_SELECT_ONE_EMPLOYEE = "SELECT * from EMPLOYEES WHERE ID = ?";
    public static final String Query_DELETE_ONE_EMPLOYEE ="DELETE FROM EMPLOYEES WHERE ID = ?";
    public static final String Query_UPDATE_ONE_EMPLOYEE ="UPDATE EMPLOYEES SET LASTNAME = ?, FIRSTNAME = ?, HOMEPHONE = ?, MOBILEPHONE = ?, WORKPHONE = ?, ADDRESS = ?, ZIPCODE = ?, CITY = ?, MAIL = ? WHERE ID = ?";
    public static final String Query_ADD_ONE_EMPLOYEE ="INSERT INTO EMPLOYEES(LASTNAME, FIRSTNAME, HOMEPHONE, MOBILEPHONE, WORKPHONE, ADDRESS, ZIPCODE, CITY, MAIL) VALUES (?,?,?,?,?,?,?,?,?)";
}
