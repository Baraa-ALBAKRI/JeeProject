package dbModels;

import entities.Employees;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import utils.AppException;
import static utils.Constants.*;

/**
 * RestfulApiCaller Class is used to manage the API calls with the server
 * 
 */
public class RestfulApiCaller implements AppActions {

    private HttpURLConnection conn;

    /**
     * 
     * Read an XML string and fill up a employees list
     */
    private List<Employees> xmlReader(InputStream is) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        List<Employees> employees = new ArrayList<>();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("employees");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    Employees e = new Employees();

                    e.setAddress(eElement.getElementsByTagName("address").item(0).getTextContent());
                    e.setCity(eElement.getElementsByTagName("city").item(0).getTextContent());
                    e.setFirstname(eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    e.setLastname(eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    e.setHomephone(eElement.getElementsByTagName("homephone").item(0).getTextContent());
                    e.setId(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
                    e.setMail(eElement.getElementsByTagName("mail").item(0).getTextContent());
                    e.setMobilephone(eElement.getElementsByTagName("mobilephone").item(0).getTextContent());
                    e.setWorkphone(eElement.getElementsByTagName("workphone").item(0).getTextContent());
                    e.setZipcode(eElement.getElementsByTagName("zipcode").item(0).getTextContent());

                    employees.add(e);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            AppException.showMessageError(ex.toString());
        }
        return employees;
    }

    /**
     * Generate a XML from an employee object
     * @param e the employees object
     * @param isUpdate add the id value in the XML or not
     * @return the XML
     */
    private String xmlWriter(Employees e, boolean isUpdate) {
        String xml;

        if (isUpdate) {
            xml = "<employees>"
                    + "<address>" + e.getAddress() + "</address>"
                    + "<city>" + e.getCity() + "</city>"
                    + "<firstname>" + e.getFirstname() + "</firstname>"
                    + "<homephone>" + e.getHomephone() + "</homephone>"
                    + "<id>" + e.getId() + "</id>"
                    + "<lastname>" + e.getLastname() + "</lastname>"
                    + "<mail>" + e.getMail() + "</mail>"
                    + "<mobilephone>" + e.getMobilephone() + "</mobilephone>"
                    + "<workphone>" + e.getWorkphone() + "</workphone>"
                    + "<zipcode>" + e.getZipcode() + "</zipcode>"
                    + "</employees>";
        } else {
            xml = "<employees>"
                    + "<address>" + e.getAddress() + "</address>"
                    + "<city>" + e.getCity() + "</city>"
                    + "<firstname>" + e.getFirstname() + "</firstname>"
                    + "<homephone>" + e.getHomephone() + "</homephone>"
                    + "<lastname>" + e.getLastname() + "</lastname>"
                    + "<mail>" + e.getMail() + "</mail>"
                    + "<mobilephone>" + e.getMobilephone() + "</mobilephone>"
                    + "<workphone>" + e.getWorkphone() + "</workphone>"
                    + "<zipcode>" + e.getZipcode() + "</zipcode>"
                    + "</employees>";
        }

        return xml;
    }

    /**
     * prepare the connection to make an API call
     * @param url the API url
     * @param method the type of the call
     */
    private void changeConnexion(String url, String method) {
        try {
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            conn.setUseCaches(false);
            conn.setReadTimeout(1000);
            switch (method) {
                case "GET":
                    conn.setRequestMethod(HttpMethod.GET);
                    conn.setRequestProperty("Accept", "application/xml");
                    break;
                case "POST":
                    conn.setRequestMethod(HttpMethod.POST);
                    conn.setDoOutput(true);
                    conn.setConnectTimeout(1000);
                    conn.setRequestProperty("Content-Type", MediaType.APPLICATION_XML);
                    conn.setRequestProperty("Accept", MediaType.APPLICATION_XML);
                    break;
                case "DELETE":
                    conn.setRequestMethod(HttpMethod.DELETE);
                    conn.setRequestProperty("Accept", "application/xml");
                    break;
                case "PUT":
                    conn.setRequestMethod(HttpMethod.PUT);
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", MediaType.APPLICATION_XML);
                    break;
            }

        } catch (IOException ex) {
            AppException.showMessageError(ex.toString());
        }
    }

    /**
     * Call the delete API to remove an employee
     * @param id the id of the employee to delete
     */
    @Override
    public void deleteEmployee(int id) {
        try {
            changeConnexion(REST_URL_LIST + "/" + id + "/", "DELETE");
            xmlReader(conn.getInputStream());
            conn.disconnect();
        } catch (IOException ex) {
            AppException.showMessageError(ex.toString());
        }
    }

    /**
     * Call the update API and modify the infotmation of an employee
     * @param e the id of the employee to modify
     */
    @Override
    public void updateEmployee(Employees e) {

        try {
            changeConnexion(REST_URL_LIST + "/" + e.getId(), "PUT");

            final OutputStream outputStream = conn.getOutputStream();
            outputStream.write(xmlWriter(e, true).getBytes());
            outputStream.flush();
            if (conn.getResponseCode() != 204) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.close();
            conn.disconnect();
        } catch (IOException ex) {
            AppException.showMessageError(ex.toString());
        }

    }

    /**
     * Call the insert API to add a new employee
     * @param e the employee to add
     */
    @Override
    public void insertEmployee(Employees e) {

        try {
            changeConnexion(REST_URL_LIST, "POST");

            final OutputStream outputStream = conn.getOutputStream();
            outputStream.write(xmlWriter(e, false).getBytes());
            outputStream.flush();
            if (conn.getResponseCode() != 204) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.close();
            conn.disconnect();
        } catch (IOException ex) {
            AppException.showMessageError(ex.toString());
        }

    }

    /**
     * Get the information of one employee specific
     * @param id the id of the employee
     * @return the employee
     */
    @Override
    public Employees getEmployee(int id) {
        try {
            changeConnexion(REST_URL_LIST + "/" + id + "/", "GET");
            List<Employees> employees;
            employees = xmlReader(conn.getInputStream());
            conn.disconnect();
            if(employees.isEmpty())
            {
                return null;
            }
            return employees.get(0);
        } catch (IOException ex) {
            AppException.showMessageError(ex.toString());
        }

        return null;
    }

    /**
     * Call the API and get the list of all the employees
     * @return the list of employees
     */
    @Override
    public List getEmployees() {
        try {
            changeConnexion(REST_URL_LIST, "GET");
            List<Employees> employees;
            employees = xmlReader(conn.getInputStream());
            conn.disconnect();
            return employees;
        } catch (IOException ex) {
            AppException.showMessageError(ex.toString());
        }
        return null;
    }

}
