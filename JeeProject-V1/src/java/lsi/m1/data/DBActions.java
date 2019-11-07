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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lsi.m1.DBmodels.EmployeeBean;
import static lsi.m1.utils.Constants.*;

/**
 *
 * @author nox
 */
public class DBActions implements IDBActions{

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public DBActions() {
        creerConn();
    }
    
    private void creerConn()
    {
        try {
         if(this.conn==null)
             this.conn= DriverManager.getConnection(URL, USER_BDD, MDP_BDD);
        } catch (SQLException sqle) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

    private Statement getStatement() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return stmt;
    }
    
    private PreparedStatement prepareStatement(String req, String... parametres) throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(req);
        for(int i=0;i<parametres.length;i++)
        {
            pstmt.setString(i+1, parametres[i]);
        }
        return pstmt;    
    }
    
    
    public void runQuery(String req, String... parametres) {
        try{
            creerConn();
            PreparedStatement pstmt = prepareStatement(req, parametres);
            pstmt.execute();
        }
        catch (SQLException sqle) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

    public ResultSet getResultSet(String req, String... parametres) {
        try{
            creerConn();
            PreparedStatement pstmt = prepareStatement(req, parametres);
            return pstmt.executeQuery();
        }
        catch (SQLException sqle) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, sqle);
        }
        return null;
    }

    @Override
    public EmployeeBean getEmployee(int id) {
        rs = this.getResultSet(Query_SELECT_ONE_EMPLOYEE, Integer.toString(id));
         try {
            if(rs.next())
            {
                EmployeeBean e = new EmployeeBean();
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
            Logger.getLogger(EmployeeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }

    @Override
    public ArrayList getEmployees() {
        ArrayList<EmployeeBean> employeesList;
        employeesList = new ArrayList<>();
        rs = this.getResultSet(Query_SELECT_ALL_EMPLOYEES);
        try {
            while (rs.next()) {
                EmployeeBean e = new EmployeeBean();
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
            Logger.getLogger(EmployeeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void deleteEmployee(int id) {
        runQuery(Query_DELETE_ONE_EMPLOYEE, Integer.toString(id));
    }

    @Override
    public void updateEmployee(EmployeeBean e) {
        runQuery(Query_UPDATE_ONE_EMPLOYEE, e.getLastName(), e.getFirstName(), e.getHomePhone(), e.getMobilePhone(), e.getWorkPhone(), e.getAddress(), e.getZipCode(), e.getCity(), e.getMail(), Integer.toString(e.getId()));
    }
    
    @Override
    public void insertEmployee(EmployeeBean e) {
        runQuery(Query_ADD_ONE_EMPLOYEE, e.getLastName(), e.getFirstName(), e.getHomePhone(), e.getMobilePhone(), e.getWorkPhone(), e.getAddress(), e.getZipCode(), e.getCity(), e.getMail());
    }

}
