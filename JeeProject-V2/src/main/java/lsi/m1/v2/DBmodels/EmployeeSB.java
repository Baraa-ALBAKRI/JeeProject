
package lsi.m1.v2.DBmodels;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/** Employee Session Bean.
 * 
 *  It is used to interact with our database (CRUD).
 */
@Stateless
public class EmployeeSB {
    /** Represents the persistence context.
     * 
     *  The entity manager handles the transactions (begin/commit/stop) for the developper.
     */
    @PersistenceContext
    EntityManager em;
    
    /**Get the list of all the employees from DB.
     * 
     * @return a list of type Employees that contains all the employees.
     */
    public List getEmployees(){
        Query q = em.createQuery("SELECT e FROM Employees e");
        return q.getResultList();
    }
    
    /**Searches for a single employee in DB.
     * 
     * @param id ID of the employee to search.
     * @return an instance of Employees if it exists in the DB, null if not.
     */
    public Employees getEmployee(int id){
        return em.find(Employees.class, id);
    }
    
    /**Removes from the DB the employee if it exists.
     * 
     * @param id ID of the employee to search.
     */
    public void deleteEmployee(int id){
        Employees emp = getEmployee(id);
        em.remove(emp);
    }
    
    /**Updates the informations of a given employee in DB.
     * 
     * @param e reprensents an employee.
     */
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
    
    /**Inserts a new employee in DB.
     * 
     * @param e reprensents an employee.
     */
    public void insertEmployee(Employees e) {
        em.persist(e);
    }
    
}
