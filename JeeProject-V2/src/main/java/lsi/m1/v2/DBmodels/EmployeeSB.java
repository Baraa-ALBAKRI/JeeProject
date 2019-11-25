
package lsi.m1.v2.DBmodels;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lsi.m1.utils.AppException;

/** Employee Session Bean.
 * 
 *  It is used to interact with our database (CRUD).
 */
@Stateless
public class EmployeeSB implements AppActions{
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
    @Override
    public List getEmployees(){
        try{
            Query q = em.createQuery("SELECT e FROM Employees e");
            return q.getResultList();
        }
        catch(Exception ex){
            AppException.showMessageError(ex.toString());
        }
        return null;
    }
    
    /**Searches for a single employee in DB.
     * 
     * @param id ID of the employee to search.
     * @return an instance of Employees if it exists in the DB, null if not.
     */
    @Override
    public Employees getEmployee(int id){
        try{
            return em.find(Employees.class, id);
        }
        catch(Exception ex){
            AppException.showMessageError(ex.toString());
        }
        return null;
    }
    
    /**Removes from the DB the employee if it exists.
     * 
     * @param id ID of the employee to search.
     */
    @Override
    public void deleteEmployee(int id){
        try{
            Employees emp = getEmployee(id);
            em.remove(emp);
        }
        catch(Exception ex){
            AppException.showMessageError(ex.toString());
        }
    }
    
    /**Updates the informations of a given employee in DB.
     * 
     * @param e reprensents an employee.
     */
    @Override
    public void updateEmployee(Employees e){
        try{
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
        catch(Exception ex){
            AppException.showMessageError(ex.toString());
        }
    }
    
    /**Inserts a new employee in DB.
     * 
     * @param e reprensents an employee.
     */
    @Override
    public void insertEmployee(Employees e) {
        try{
            em.persist(e);
        }
        catch(Exception ex){
            AppException.showMessageError(ex.toString());
        }
    }
    
}
