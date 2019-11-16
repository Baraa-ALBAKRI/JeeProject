package dbModels;

import entities.Employees;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import static utils.Constants.*;

public class RestfulApiCaller implements AppActions {

    private HttpURLConnection conn;

    private List<Employees> xmlReader(InputStream is) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        List<Employees> employees = new ArrayList<Employees>();
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

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(RestfulApiCaller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(RestfulApiCaller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RestfulApiCaller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return employees;
    }

    private void changeConnexion(String url, String method) {
        try {
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod(method);
            switch (method) {
                case "GET":
                    conn.setRequestProperty("Accept", "application/xml");
                    break;
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(RestfulApiCaller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RestfulApiCaller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try {
            changeConnexion(REST_URL_LIST + "/" + id + "/", "DELETE");
            List<Employees> employees;
            employees =  xmlReader(conn.getInputStream());
            conn.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(RestfulApiCaller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateEmployee(Employees e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertEmployee(Employees e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employees getEmployee(int id) {
        try {
            changeConnexion(REST_URL_LIST + "/" + id + "/", "GET");
            List<Employees> employees;
            employees =  xmlReader(conn.getInputStream());
            conn.disconnect();
            return employees.get(0);
        } catch (IOException ex) {
            Logger.getLogger(RestfulApiCaller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public List getEmployees() {
        try {
            changeConnexion(REST_URL_LIST, "GET");
            List<Employees> employees;
            employees =  xmlReader(conn.getInputStream());
            conn.disconnect();
            return employees;
        } catch (IOException ex) {
            Logger.getLogger(RestfulApiCaller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}