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
}
