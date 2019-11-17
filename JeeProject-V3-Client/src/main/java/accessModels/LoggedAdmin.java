
package accessModels;

import dbModels.AppActions;
import entities.Employees;

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
     * @param appActions used to interact with the DB.
     * @return the employee if it exists, else null.
     */
    public Employees getEmployee(int id, AppActions appActions){
        return appActions.getEmployee(id);
    }
    /**Deletes an Employee from its ID in DB.
     * 
     * @param id
     * @param appActions used to interact with the DB. 
     * @return delete status
     */
    public boolean deleteEmployee(int id, AppActions appActions) {
        if(appActions.getEmployee(id) != null){
            appActions.deleteEmployee(id);
            return true;
        }
        return false;
    }
     /**Updates an employee in DB.
     * 
     * @param e
     * @param appActions used to interact with the DB. 
     * @return modification status
     */
    public boolean modifyEmployee(Employees e, AppActions appActions) {
        if(appActions.getEmployee(e.getId()) != null){
            appActions.updateEmployee(e);
            return true;
        }
        return false;
    }
    /**Inserts an employee in DB.
     * 
     * @param e
     * @param appActions used to interact with the DB. 
     */
    public void addEmployee(Employees e, AppActions appActions) {
        appActions.insertEmployee(e);
    }
}
