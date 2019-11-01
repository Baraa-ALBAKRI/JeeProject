/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.models;

/**
 *
 * @author nox
 */
public class LoggedAdmin extends LoggedEmployee {

    public LoggedAdmin() {
        super.setAccessLevel("admin");
    }

    public int deleteEmployee(int id, DBActions db) {
        return db.executeStatement("DELETE FROM EMPLOYES WHERE ID = " + id);
    }
    
    public EmployeeBean getEmployee(int id, DBActions db){
        return db.getEmployee(id);
    }

    public int addEmployee(EmployeeBean e, DBActions db) {
        String req;

        req = "INSERT INTO EMPLOYES(NOM,PRENOM,TELDOM,TELPORT,TELPRO,ADRESSE,CODEPOSTAL,VILLE,EMAIL) VALUES"
                + "('" + e.getLastName() + "',"
                + "'" + e.getFirstName() + "',"
                + "'" + e.getHomePhone() + "',"
                + "'" + e.getMobilePhone() + "',"
                + "'" + e.getWorkPhone() + "',"
                + "'" + e.getAddress() + "',"
                + "'" + e.getZipCode() + "',"
                + "'" + e.getCity() + "',"
                + "'" + e.getMail() + "')";
        
        //return req;
        return db.executeStatement(req);
    }

    public int modifyEmployee(EmployeeBean e, DBActions db) {
        String req;

        req = "UPDATE EMPLOYES SET NOM = '" + e.getLastName() + "',"
                + "PRENOM = '" + e.getFirstName() + "',"
                + "TELDOM = '" + e.getHomePhone() + "',"
                + "TELPORT = '" + e.getMobilePhone() + "',"
                + "TELPRO = '" + e.getWorkPhone() + "',"
                + "ADRESSE = '" + e.getAddress() + "',"
                + "CODEPOSTAL ='" + e.getZipCode() + "',"
                + "VILLE = '" + e.getCity() + "',"
                + "EMAIL = '" + e.getMail() + "'"
                + " WHERE ID = " + e.getId();

        return db.executeStatement(req);
    }
}
