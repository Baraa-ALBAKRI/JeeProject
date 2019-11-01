/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static lsi.m1.utils.Constants.*;

/**
 *
 * @author nox
 */
public class DBActions {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public DBActions() {
        try {
            conn = DriverManager.getConnection(URL, USER_BDD, MDP_BDD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Statement getStatement() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return stmt;
    }

    public ResultSet getResultSet(String req) {
        try {
            stmt = getStatement();
            rs = stmt.executeQuery(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public EmployeeBean getEmployee(int id) {
        rs = getResultSet("SELECT * FROM EMPLOYES WHERE ID =" + id);

        try {
            if (rs.next()) {
                EmployeeBean e = new EmployeeBean();
                e.setId(rs.getInt("ID"));
                e.setLastName(rs.getString("NOM"));
                e.setFirstName(rs.getString("PRENOM"));
                e.setHomePhone(rs.getString("TELDOM"));
                e.setMobilePhone(rs.getString("TELPORT"));
                e.setWorkPhone(rs.getString("TELPRO"));
                e.setAddress(rs.getString("ADRESSE"));
                e.setZipCode(rs.getString("CODEPOSTAL"));
                e.setCity(rs.getString("VILLE"));
                e.setMail(rs.getString("EMAIL"));
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList getEmployees() {
        ArrayList<EmployeeBean> employeesList;
        employeesList = new ArrayList<>();

        try {
            rs = getResultSet("SELECT * FROM EMPLOYES");

            while (rs.next()) {
                EmployeeBean e = new EmployeeBean();
                e.setId(rs.getInt("ID"));
                e.setLastName(rs.getString("NOM"));
                e.setFirstName(rs.getString("PRENOM"));
                e.setHomePhone(rs.getString("TELDOM"));
                e.setMobilePhone(rs.getString("TELPORT"));
                e.setWorkPhone(rs.getString("TELPRO"));
                e.setAddress(rs.getString("ADRESSE"));
                e.setZipCode(rs.getString("CODEPOSTAL"));
                e.setCity(rs.getString("VILLE"));
                e.setMail(rs.getString("EMAIL"));
                employeesList.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employeesList;
    }

    public int executeStatement(String req) {
        try {
            stmt = getStatement();
            return stmt.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
