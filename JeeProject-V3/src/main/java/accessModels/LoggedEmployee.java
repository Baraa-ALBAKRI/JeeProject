
package accessModels;

import java.util.List;
import dbModels.AppActions;
import entities.Employees;

/**Represents a logged Employee.
 * He can read other employees.
 */
public class LoggedEmployee {
    /**Access Level of the User.*/
    private String accessLevel;
    
    public LoggedEmployee(){
        this("employee");
    }
    
    protected LoggedEmployee(String accessLevel){
        setAccessLevel(accessLevel);
    }
    
    /**Get the list of all the employees.
     * 
     * @param appActions Session Bean used to interact with DB.
     * @return the list of all the employees in DB.
     */
    public List<Employees> getEmployeesList(AppActions appActions){
        return appActions.getEmployees();
    }

    /**Get access level of the logged class
     * 
     * @return the accessLevel
     */
    public String getAccessLevel() {
        return accessLevel;
    }

    /**Set the access level by the constructor
     * 
     */
    private void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
