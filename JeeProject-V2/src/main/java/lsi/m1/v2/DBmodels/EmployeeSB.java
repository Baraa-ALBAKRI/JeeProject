/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.v2.DBmodels;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mhdba
 */
@Stateless
public class EmployeeSB {

    @PersistenceContext
    EntityManager em;
    
    public List getEmployees(){
        Query q = em.createQuery("SELECT e FROM Employees e");
        return q.getResultList();
    }
    
    public Employees getEmployee(int id){
        return em.find(Employees.class, id);
    }
    
    public void deleteEmployee(int id){
        Employees emp = getEmployee(id);
        em.remove(emp);
    }
    
    public void modifyEmployee(Employees e){
        Employees emp = getEmployee(e.getId());
        emp.setLastname(e.getLastname());
        emp.setFirstname(e.getFirstname());
        emp.setHomephone(e.getHomephone());
        emp.setMobilephone(e.getMobilephone());
        emp.setWorkphone(e.getWorkphone());
        emp.setAddress(e.getAddress());
        emp.setZipcode(e.getZipcode());
        emp.setCity(e.getCity());
        emp.setMail(e.getMail());
    }
    
    public void insertEmployee(Employees e) {
        em.persist(e);
    }
    
}
