
package lsi.m1.v2.accessModels;

import lsi.m1.v2.DBmodels.EmployeeSB;
import lsi.m1.v2.DBmodels.Employees;

/**Represents a logged administator.
 * An admin has the full control on the DB.
 * He can create,read,update and delete employees.
 */
public class LoggedAdmin extends LoggedEmployee {

    public LoggedAdmin() {
        super("admin");
    }
    /**Returns an Employee from the DB using its ID.
     * 
     * @param id
     * @param employeeSB used to interact with the DB.
     * @return the employee if it exists, else null.
     */
    public Employees getEmployee(int id, EmployeeSB employeeSB){
        return employeeSB.getEmployee(id);
    }
    /**Deletes an Employee from its ID in DB.
     * 
     * @param id
     * @param employeeSB used to interact with the DB. 
     */
    public void deleteEmployee(int id, EmployeeSB employeeSB) {
        employeeSB.deleteEmployee(id);
    }
     /**Updates an employee in DB.
     * 
     * @param e
     * @param employeeSB used to interact with the DB. 
     */
    public void modifyEmployee(Employees e, EmployeeSB employeeSB) {
        employeeSB.modifyEmployee(e);
    }
    /**Inserts an employee in DB.
     * 
     * @param e
     * @param employeeSB used to interact with the DB. 
     */
    public void addEmployee(Employees e, EmployeeSB employeeSB) {
        employeeSB.insertEmployee(e);
    }
}
