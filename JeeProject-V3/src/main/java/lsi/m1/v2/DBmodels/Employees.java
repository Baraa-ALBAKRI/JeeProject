
package lsi.m1.v2.DBmodels;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "employees")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e"),
    @NamedQuery(name = "Employees.findById", query = "SELECT e FROM Employees e WHERE e.id = :id"),
    @NamedQuery(name = "Employees.findByLastname", query = "SELECT e FROM Employees e WHERE e.lastname = :lastname"),
    @NamedQuery(name = "Employees.findByFirstname", query = "SELECT e FROM Employees e WHERE e.firstname = :firstname"),
    @NamedQuery(name = "Employees.findByHomephone", query = "SELECT e FROM Employees e WHERE e.homephone = :homephone"),
    @NamedQuery(name = "Employees.findByMobilephone", query = "SELECT e FROM Employees e WHERE e.mobilephone = :mobilephone"),
    @NamedQuery(name = "Employees.findByWorkphone", query = "SELECT e FROM Employees e WHERE e.workphone = :workphone"),
    @NamedQuery(name = "Employees.findByAddress", query = "SELECT e FROM Employees e WHERE e.address = :address"),
    @NamedQuery(name = "Employees.findByZipcode", query = "SELECT e FROM Employees e WHERE e.zipcode = :zipcode"),
    @NamedQuery(name = "Employees.findByCity", query = "SELECT e FROM Employees e WHERE e.city = :city"),
    @NamedQuery(name = "Employees.findByMail", query = "SELECT e FROM Employees e WHERE e.mail = :mail")})
/**Employees class. Represents an Employee.
 *
 */
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "HOMEPHONE")
    private String homephone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MOBILEPHONE")
    private String mobilephone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "WORKPHONE")
    private String workphone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ZIPCODE")
    private String zipcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "CITY")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "MAIL")
    private String mail;

    public Employees() {
    }
    /**Creates an instance of Employee only from an ID.
     * 
     * @param id 
     */
    public Employees(Integer id) {
        this.id = id;
    }
    /**Creates an instance of Employee from the informations given.
     * 
     * @param id
     * @param lastname
     * @param firstname
     * @param homephone
     * @param mobilephone
     * @param workphone
     * @param address
     * @param zipcode
     * @param city
     * @param mail 
     */
    public Employees(Integer id, String lastname, String firstname, String homephone, String mobilephone, String workphone, String address, String zipcode, String city, String mail) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.homephone = homephone;
        this.mobilephone = mobilephone;
        this.workphone = workphone;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.mail = mail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lsi.m1.v2.DBmodels.Employees[ id=" + id + " ]";
    }
    
}
