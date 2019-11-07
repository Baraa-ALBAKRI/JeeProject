/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.data;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import lsi.m1.DBmodels.EmployeeBean;
import lsi.m1.entities.Employees;

/**
 *
 * @author nicob
 */
@Stateless
public class DBActions implements IDBActions{
     @PersistenceContext     
     EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void deleteEmployee(int id) {
        TypedQuery<Employees> q = em.createNamedQuery("Employees.findById",Employees.class).setParameter("id", id);
        Employees e = q.getSingleResult();
        em.remove(q.getSingleResult());
    }

    @Override
    public void updateEmployee(EmployeeBean e) {
        
    }

    @Override
    public void insertEmployee(EmployeeBean e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmployeeBean getEmployee(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getEmployees() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
