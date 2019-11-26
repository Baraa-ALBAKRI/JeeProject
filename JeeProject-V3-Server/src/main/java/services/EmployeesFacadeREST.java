
package services;

import entities.Employees;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * EmployeesFacadeREST class (auto generated)
 * In this class, you can find all the possible API call of our application
 */
@Stateless
@Path("entities.employees")
public class EmployeesFacadeREST extends AbstractFacade<Employees> {

    @PersistenceContext(unitName = "com.efrei.lsi.m1_JeeProject-V3_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public EmployeesFacadeREST() {
        super(Employees.class);
    }

    /**
     * Create a new employee
     * @param entity the employee to create 
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Employees entity) {
        super.create(entity);
    }

    /**
     * Modify an employee
     * @param id the id of the employee
     * @param entity his new informations
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Employees entity) {
        super.edit(entity);
    }

    /**
     * Delete an employee
     * @param id the id of the employee
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    /**
     * Get the informations of one employee
     * @param id the id of the employee
     * @return the employee
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Employees find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    /**
     * Get the informations of all the employees
     * @return the employees
     */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Employees> findAll() {
        return super.findAll();
    }

    /**
     * Get a part of the employees
     * @param from 
     * @param to
     * @return the employees
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Employees> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     * Get the total number of employees
     * @return the number of employees
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
