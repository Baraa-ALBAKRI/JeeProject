
package lsi.m1.v2.accessModels;

import java.util.List;
import lsi.m1.v2.DBmodels.EmployeeSB;
import lsi.m1.v2.DBmodels.Employees;

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
     * @param db Session Bean used to interact with DB.
     * @return the list of all the employees in DB.
     */
    public List<Employees> getEmployeesList(EmployeeSB db){
        return db.getEmployees();
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    private void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
