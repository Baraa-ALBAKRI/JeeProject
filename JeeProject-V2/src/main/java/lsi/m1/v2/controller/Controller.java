/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.v2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static lsi.m1.utils.Constants.*;
import lsi.m1.v2.DBmodels.AppActions;
import lsi.m1.v2.DBmodels.Employees;
import lsi.m1.v2.accessModels.LoggedAdmin;
import lsi.m1.v2.accessModels.LoggedEmployee;

/**
 * Controller class from the JEE Web Application
 *
 */
public class Controller extends HttpServlet {

    @EJB
    private AppActions appActions;
    HttpSession session;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            session = request.getSession();
            //By default, the logged user is an employee.
            LoggedEmployee loggedUser = (LoggedEmployee) session.getAttribute("loggedUser");

            //If the user is not logged then we try to get the login informations from the login page.
            if (loggedUser == null) {
                //Login form informations
                String login = request.getParameter(FRM_LOGIN);
                String password = request.getParameter(FRM_PASSWORD);
                //If it empty, it displays an error message and it goes back to the login page.
                if (login == null || password == null) {
                    session.setAttribute("errKey", ERR_LOGIN);
                    response.sendRedirect("login.jsp");
                } else if (login.equals(getServletContext().getInitParameter("loginAdmin")) && password.equals(getServletContext().getInitParameter("passwordAdmin"))) {
                    //If it corresponds to the login information of an Admin then the loggedUser becomes an admin.
                    loggedUser = new LoggedAdmin();
                    session.setAttribute("loggedUser", loggedUser);
                    session.removeAttribute("errKey");
                } else if (login.equals(getServletContext().getInitParameter("loginEmployee")) && password.equals(getServletContext().getInitParameter("passwordEmployee"))) {
                    //If it corresponds to the login information of an Employee then the loggedUser becomes an Employee.
                    loggedUser = new LoggedEmployee();
                    session.setAttribute("loggedUser", loggedUser);
                    session.removeAttribute("errKey");
                }
                //If no user can be logged, it displays an error message and goes back to the login page.
                if (loggedUser == null) {
                    session.removeAttribute("loginLevel");
                    session.setAttribute("errKey", ERR_LOGIN);
                    response.sendRedirect("login.jsp");
                } else {
                    //Otherwise, it displays the list of employees (redirection to employeeList.jsp
                    session.setAttribute("employeesList", loggedUser.getEmployeesList(appActions));
                    response.sendRedirect("employeesList.jsp");
                }
            } else {
                //If the user is already logged, we react to his actions.
                String action = request.getParameter(BTN_NAME);
                if (action != null) {
                    //Trying to get the selected employee's ID.
                    int id = Integer.parseInt(request.getParameter(RADIO_BTN) != null ? request.getParameter(RADIO_BTN) : "-1");
                    session.removeAttribute("selectStatus");
                    LoggedAdmin loggedAdmin;

                    switch (action) {
                        //The user wants to delete an employee
                        case "Supprimer":
                            loggedAdmin = (LoggedAdmin) loggedUser;
                            //If he selected an employee, it removes it and displays a completion message.
                            if (id > -1) {
                                if(loggedAdmin.deleteEmployee(id, appActions)){
                                    session.setAttribute("selectStatusColor", "green");
                                    session.setAttribute("selectStatus", SUPP_OK);
                                }
                                else{
                                    session.setAttribute("selectStatusColor", "red");
                                    session.setAttribute("selectStatus", SUPP_KO);
                                }
                            } else {
                                //Else it displays an error.
                                session.setAttribute("selectStatusColor", "red");
                                session.setAttribute("selectStatus", ERR_SELECT);
                            }
                            // Refresh the employees list
                            session.setAttribute("employeesList", loggedUser.getEmployeesList(appActions));
                            response.sendRedirect("employeesList.jsp");

                            break;
                        //The user wants details about an employee
                        case "Details":
                            loggedAdmin = (LoggedAdmin) loggedUser;
                            //If he selected an employee, it displays it.
                            if (id > -1) {
                                Employees e = loggedAdmin.getEmployee(id, appActions);
                                if(e != null){
                                    session.setAttribute("buttonValue", "Modifier");
                                    session.setAttribute("employe", e);
                                    session.removeAttribute("selectStatus");
                                    response.sendRedirect("employeeDetails.jsp");
                                }
                                else{
                                    session.setAttribute("selectStatus", ERR_DONT_EXIST);
                                    session.setAttribute("selectStatusColor", "red");
                                    session.setAttribute("employeesList", loggedUser.getEmployeesList(appActions));
                                    response.sendRedirect("employeesList.jsp");
                                }
                            } else {
                                //Else it displays an error message.
                                session.setAttribute("selectStatus", ERR_SELECT);
                                session.setAttribute("selectStatusColor", "red");
                                session.setAttribute("employeesList", loggedUser.getEmployeesList(appActions));
                                response.sendRedirect("employeesList.jsp");
                            }
                            break;
                        //The user wants to add an employee
                        case "Ajouter":
                            loggedAdmin = (LoggedAdmin) loggedUser;
                            //If the form to create a new employee is filled, then we insert this employee in DB.
                            if (session.getAttribute("buttonValue").equals("Ajouter")) {
                                Employees e = new Employees();

                                e.setLastname(request.getParameter(LN_FRM));
                                e.setFirstname(request.getParameter(FN_FRM));
                                e.setHomephone(request.getParameter(HP_FRM));
                                e.setMobilephone(request.getParameter(MP_FRM));
                                e.setWorkphone(request.getParameter(WP_FRM));
                                e.setAddress(request.getParameter(ADDR_FRM));
                                e.setZipcode(request.getParameter(ZIP_FRM));
                                e.setCity(request.getParameter(CITY_FRM));
                                e.setMail(request.getParameter(MAIL_FRM));
                                //If the informations respect what we expect it inserts it and displays a completion message (on the refreshed list).
                                if (verifyInputForm(e)) {
                                    loggedAdmin.addEmployee(e, appActions);
                                    session.setAttribute("buttonValue", "");
                                    session.setAttribute("employeesList", loggedUser.getEmployeesList(appActions));
                                    session.setAttribute("selectStatusColor", "green");
                                    session.setAttribute("selectStatus", ADD_OK);
                                    response.sendRedirect("employeesList.jsp");
                                } else {
                                    //Otherwise it displays an error message
                                    session.setAttribute("selectStatusColor", "red");
                                    session.setAttribute("selectStatus", FORM_KO);
                                    session.removeAttribute("employe");
                                    response.sendRedirect("employeeDetails.jsp");
                                }
                            } else {
                                //If the form is not filled, it redirects the user to it.
                                session.setAttribute("buttonValue", "Ajouter");
                                session.removeAttribute("selectStatus");
                                session.removeAttribute("employe");
                                response.sendRedirect("employeeDetails.jsp");
                            }

                            break;
                        //The user wants to modify an employee
                        case "Modifier":
                            loggedAdmin = (LoggedAdmin) loggedUser;
                            Employees e = new Employees();
                            Employees oldE = (Employees) session.getAttribute("employe");
                            //We get the informations of the oldEmployee (actually on its ID) and we create an new Employee with the new informations
                            e.setId(oldE.getId());
                            e.setLastname(request.getParameter(LN_FRM));
                            e.setFirstname(request.getParameter(FN_FRM));
                            e.setHomephone(request.getParameter(HP_FRM));
                            e.setMobilephone(request.getParameter(MP_FRM));
                            e.setWorkphone(request.getParameter(WP_FRM));
                            e.setAddress(request.getParameter(ADDR_FRM));
                            e.setZipcode(request.getParameter(ZIP_FRM));
                            e.setCity(request.getParameter(CITY_FRM));
                            e.setMail(request.getParameter(MAIL_FRM));
                            //If the informations respect what we expect it inserts it and displays a completion message (on the refreshed list).
                            if (verifyInputForm(e)) {
                                if(loggedAdmin.modifyEmployee(e, appActions)){
                                    session.setAttribute("selectStatusColor", "green");
                                    session.setAttribute("selectStatus", UPDT_OK);
                                }else{
                                    session.setAttribute("selectStatusColor", "red");
                                    session.setAttribute("selectStatus", UPDT_KO);
                                }
                                session.setAttribute("employeesList", loggedUser.getEmployeesList(appActions));
                                response.sendRedirect("employeesList.jsp");
                            } else {
                                //Otherwise it displays an error message
                                session.setAttribute("selectStatusColor", "red");
                                session.setAttribute("selectStatus", FORM_KO);
                                response.sendRedirect("employeeDetails.jsp");
                            }
                            break;
                        //Shows the list of employees
                        case "Voir liste":

                            session.setAttribute("employeesList", loggedUser.getEmployeesList(appActions));
                            response.sendRedirect("employeesList.jsp");

                            break;
                        //Redirect to the logout page, and removes all the session attributes
                        case "Deconnexion":

                            Enumeration<String> sessionsAttributeNames = session.getAttributeNames();

                            while (sessionsAttributeNames.hasMoreElements()) {
                                session.removeAttribute(sessionsAttributeNames.nextElement());
                            }

                            response.sendRedirect("logout.jsp");

                            break;
                        //Unhandled action
                        default:
                            out.println("[" + action + "]");
                    }
                }
            }
        }
    }

    /**
     * Checker whether an employee's attributes respect what we expect.
     *
     * @param e employee to check.
     * @return boolean that indicates if the inputs are valid.
     */
    private boolean verifyInputForm(Employees e) {
        if (e.getLastname() != null
                && e.getFirstname() != null
                && e.getHomephone() != null
                && e.getMobilephone() != null
                && e.getWorkphone() != null
                && e.getAddress() != null
                && e.getZipcode() != null
                && e.getCity() != null
                && e.getMail() != null) {
            if (contentJustNumber(e.getHomephone())
                    && contentJustNumber(e.getMobilephone())
                    && contentJustNumber(e.getWorkphone())
                    && contentJustNumber(e.getZipcode())
                    && e.getZipcode().length() < 6
                    && e.getMail().contains("@")
                    && e.getMail().contains(".")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pattern matching on number.
     *
     * @param s string to check.
     * @return a boolean that indicates if it is only numbers.
     */
    private boolean contentJustNumber(String s) {
        return Pattern.matches("[0-9 ]*", s);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
