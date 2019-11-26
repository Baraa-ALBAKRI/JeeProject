/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lsi.m1.DBmodels.Employees;
import lsi.m1.utils.AppException;
import static lsi.m1.utils.Constants.*;

/**
 *DBActions represent all the possible actions to run on the database
 */
public class DBActions implements AppActions{

    private Connection conn;
    private ResultSet rs;

    public DBActions() {
        creerConn();
    }
    /**
     * To create a new connection with the database if the application don't have already one
     */
    private void creerConn()
    {
        try {
         if(this.conn==null)
             this.conn= DriverManager.getConnection(URL, USER_BDD, MDP_BDD);
        } catch (SQLException ex) {
            AppException.showMessageError(ex.toString());
        }
    }
    
    /**
     * Add the list of parametres to the query
     * @param req the query to execute on the database
     * @param parametres the list of parametre
     * @return the full query
     * @throws SQLException 
     */
    private PreparedStatement prepareStatement(String req, String... parametres) 
            throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(req);
        for(int i=0;i<parametres.length;i++)
        {
            pstmt.setString(i+1, parametres[i]);
        }
        return pstmt;    
    }
    
    /**
     * Execute a query with a list of parametres(or without any parametres)
     * @param req the query
     * @param parametres the list of parametres
     * @throws SQLException 
     */
    public void runQuery(String req, String... parametres) throws SQLException {
            creerConn();
            PreparedStatement pstmt = prepareStatement(req, parametres);
            pstmt.execute();
    }

    /**
     * Execute a query with a list of parametres(or without any parametres)
     * @param req the query
     * @param parametres the list of parametres
     * @return the result of the execution
     * @throws SQLException 
     */
    public ResultSet getResultSet(String req, String... parametres) throws SQLException {
        creerConn();
        PreparedStatement pstmt = prepareStatement(req, parametres);
        return pstmt.executeQuery();
    }

    /**Searches for a single employee in DB.
     * 
     * @param id ID of the employee to search.
     * @return an instance of Employees if it exists in the DB, null if not.
     */
    @Override
    public Employees getEmployee(int id) {
        try {
            rs = this.getResultSet(QUERY_SELECT_ONE_EMPLOYEE, Integer.toString(id));
            if(rs.next())
            {
                Employees e = new Employees();
                e.setId(rs.getInt("ID"));
                e.setLastName(rs.getString("LASTNAME"));
                e.setFirstName(rs.getString("FIRSTNAME"));
                e.setHomePhone(rs.getString("HOMEPHONE"));
                e.setMobilePhone(rs.getString("MOBILEPHONE"));
                e.setWorkPhone(rs.getString("WORKPHONE"));
                e.setAddress(rs.getString("ADDRESS"));
                e.setZipCode(rs.getString("ZIPCODE"));
                e.setCity(rs.getString("CITY"));
                e.setMail(rs.getString("MAIL"));
                return e;
            }
            else
                return null;
        } catch (SQLException ex) {
            AppException.showMessageError(ex.toString());
        }
         return null;
    }
    
    /**Get the list of all the employees from DB.
     * 
     * @return a list of type Employees that contains all the employees.
     */
    @Override
    public ArrayList getEmployees() {
        ArrayList<Employees> employeesList;
        employeesList = new ArrayList<>();
        try {
            rs = this.getResultSet(QUERY_SELECT_ALL_EMPLOYEES);
            while (rs.next()) {
                Employees e = new Employees();
                e.setId(rs.getInt("ID"));
                e.setLastName(rs.getString("LASTNAME"));
                e.setFirstName(rs.getString("FIRSTNAME"));
                e.setHomePhone(rs.getString("HOMEPHONE"));
                e.setMobilePhone(rs.getString("MOBILEPHONE"));
                e.setWorkPhone(rs.getString("WORKPHONE"));
                e.setAddress(rs.getString("ADDRESS"));
                e.setZipCode(rs.getString("ZIPCODE"));
                e.setCity(rs.getString("CITY"));
                e.setMail(rs.getString("MAIL"));
                employeesList.add(e);
            }
            return employeesList;
        } catch (SQLException ex) {
            AppException.showMessageError(ex.toString());
        }
        return null;
    }

    /**Removes from the DB the employee if it exists.
     * 
     * @param id ID of the employee to search.
     */
    @Override
    public void deleteEmployee(int id) {
        try {
            runQuery(QUERY_DELETE_ONE_EMPLOYEE, Integer.toString(id));
        } catch (SQLException ex) {
            AppException.showMessageError(ex.toString());
        }
    }

    /**Updates the informations of a given employee in DB.
     * 
     * @param e reprensents an employeeBean.
     */
    @Override
    public void updateEmployee(Employees e) {
        try {
            runQuery(QUERY_UPDATE_ONE_EMPLOYEE, e.getLastName(), e.getFirstName(), e.getHomePhone()
                    , e.getMobilePhone(), e.getWorkPhone(), e.getAddress(), e.getZipCode()
                    , e.getCity(), e.getMail(), Integer.toString(e.getId()));
        } catch (SQLException ex) {
            AppException.showMessageError(ex.toString());
        }
    }
    
    /**Inserts a new employee in DB.
     * 
     * @param e reprensents an employeeBean.
     */
    @Override
    public void insertEmployee(Employees e) {
        try {
            runQuery(QUERY_ADD_ONE_EMPLOYEE, e.getLastName(), e.getFirstName(), e.getHomePhone()
                    , e.getMobilePhone(), e.getWorkPhone(), e.getAddress(), e.getZipCode()
                    , e.getCity(), e.getMail());
        } catch (SQLException ex) {
            AppException.showMessageError(ex.toString());
        }
    }

}
