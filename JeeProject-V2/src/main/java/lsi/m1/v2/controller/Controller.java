/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.v2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static lsi.m1.utils.Constants.FRM_LOGIN;
import static lsi.m1.utils.Constants.FRM_PASSWORD;
import lsi.m1.v2.DBmodels.EmployeeSB;
import lsi.m1.v2.DBmodels.Employees;
import lsi.m1.v2.accessModels.LoggedAdmin;
import lsi.m1.v2.accessModels.LoggedEmployee;

/**
 *
 * @author mhdba
 */
public class Controller extends HttpServlet {
    @EJB
    private EmployeeSB employeeSB;
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
            LoggedEmployee loggedUser = (LoggedEmployee) session.getAttribute("loggedUser");
            if (loggedUser == null) {
                String login = request.getParameter(FRM_LOGIN);
                String password = request.getParameter(FRM_PASSWORD);
                if (login == null || password == null) {
                    session.setAttribute("errKey", "Echec de la connexion ! Vérifiez votre login et/ou mot de passe et essayez à nouveau.");
                    response.sendRedirect("login.jsp");
                } else if (login.equals(getServletContext().getInitParameter("loginAdmin")) && password.equals(getServletContext().getInitParameter("passwordAdmin"))) {
                    loggedUser = new LoggedAdmin();
                    session.setAttribute("loggedUser", loggedUser);
                    session.setAttribute("errKey", "");
                } else if (login.equals(getServletContext().getInitParameter("loginEmployee")) && password.equals(getServletContext().getInitParameter("passwordEmployee"))) {
                    loggedUser = new LoggedEmployee();
                    session.setAttribute("loggedUser", loggedUser);
                    session.setAttribute("errKey", "");
                }
                if (loggedUser == null) {
                    session.setAttribute("loginLevel", "");
                    session.setAttribute("errKey", "Echec de la connexion ! Vérifiez votre login et/ou mot de passe et essayez à nouveau.");
                    response.sendRedirect("login.jsp");
                } else {
                    session.setAttribute("employeesList", loggedUser.getEmployeesList(employeeSB));
                    response.sendRedirect("employeesList.jsp");
                }
            } //Not acceil page
            
            else {
                String action = request.getParameter("button");
                if (action != null) {
                    int id = Integer.parseInt(request.getParameter("selector") != null ? request.getParameter("selector") : "-1");
                    session.setAttribute("selectStatus", "");
                    LoggedAdmin loggedAdmin;
                    switch (action) {
                        
                        case "Supprimer":
                            loggedAdmin = (LoggedAdmin) loggedUser;
                            if (id > -1) {
                                loggedAdmin.deleteEmployee(id, employeeSB);
                                session.setAttribute("selectStatusColor", "green");
                                session.setAttribute("selectStatus", "Suppression réussie.");
                            } else {
                                session.setAttribute("selectStatusColor", "red");
                                session.setAttribute("selectStatus", "Veuillez sélectionner un employé.");
                            }
                            
                            session.setAttribute("employeesList", loggedUser.getEmployeesList(employeeSB));
                            response.sendRedirect("employeesList.jsp");
                            
                            break;
                            
                        case "Details":
                            loggedAdmin = (LoggedAdmin) loggedUser;
                            if (id > -1) {
                                session.setAttribute("buttonValue", "Modifier");
                                session.setAttribute("employe", loggedAdmin.getEmployee(id, employeeSB));
                                session.setAttribute("selectStatus", null);
                                response.sendRedirect("employeeDetails.jsp");
                            }
                            
                            else {
                                session.setAttribute("selectStatusColor", "red");
                                session.setAttribute("selectStatus", "Veuillez sélectionner un employé.");
                                session.setAttribute("employeesList", loggedUser.getEmployeesList(employeeSB));
                                response.sendRedirect("employeesList.jsp");
                            }

                            break;
                            
                        case "Ajouter":
                            loggedAdmin = (LoggedAdmin) loggedUser;
                            if (session.getAttribute("buttonValue").equals("Ajouter")) {
                                Employees e = new Employees();

                                e.setLastname(request.getParameter("lastNameInput"));
                                e.setFirstname(request.getParameter("firstNameInput"));
                                e.setHomephone(request.getParameter("homePhoneInput"));
                                e.setMobilephone(request.getParameter("mobilePhoneInput"));
                                e.setWorkphone(request.getParameter("workPhoneInput"));
                                e.setAddress(request.getParameter("addressInput"));
                                e.setZipcode(request.getParameter("zipInput"));
                                e.setCity(request.getParameter("cityInput"));
                                e.setMail(request.getParameter("mailInput"));
                                
                                if(verifyInputForm(e)){
                                loggedAdmin.addEmployee(e, employeeSB);
                                    session.setAttribute("buttonValue", "");
                                    session.setAttribute("employeesList", loggedUser.getEmployeesList(employeeSB));
                                    session.setAttribute("selectStatusColor", "green");
                                    session.setAttribute("selectStatus", "Ajout réussie.");
                                    response.sendRedirect("employeesList.jsp");
                                }
                                else{
                                    session.setAttribute("selectStatusColor", "red");
                                    session.setAttribute("selectStatus", "Merci de remplir le formulaire avec des informations valides.");
                                    session.setAttribute("employe", null);
                                    response.sendRedirect("employeeDetails.jsp");
                                }
                            } 
                            
                            else {
                                
                                session.setAttribute("buttonValue", "Ajouter");
                                session.setAttribute("selectStatus", null);
                                session.setAttribute("employe", null);
                                response.sendRedirect("employeeDetails.jsp");
                            }

                            break;
                        case "Modifier":
                            loggedAdmin = (LoggedAdmin) loggedUser;
                            Employees e = new Employees();
                            Employees oldE = (Employees) session.getAttribute("employe");
                            e.setId(oldE.getId());
                            e.setLastname(request.getParameter("lastNameInput"));
                            e.setFirstname(request.getParameter("firstNameInput"));
                            e.setHomephone(request.getParameter("homePhoneInput"));
                            e.setMobilephone(request.getParameter("mobilePhoneInput"));
                            e.setWorkphone(request.getParameter("workPhoneInput"));
                            e.setAddress(request.getParameter("addressInput"));
                            e.setZipcode(request.getParameter("zipInput"));
                            e.setCity(request.getParameter("cityInput"));
                            e.setMail(request.getParameter("mailInput"));
                            if(verifyInputForm(e)){
                            loggedAdmin.modifyEmployee(e, employeeSB);
                                session.setAttribute("employeesList", loggedUser.getEmployeesList(employeeSB));
                                session.setAttribute("selectStatusColor", "green");
                                session.setAttribute("selectStatus", "Modification réussie.");
                                response.sendRedirect("employeesList.jsp");
                            }
                            else{
                                session.setAttribute("selectStatusColor", "red");
                                session.setAttribute("selectStatus", "Merci de remplir le formulaire avec des informations valides.");
                                response.sendRedirect("employeeDetails.jsp");
                            }
                            break;
                            
                        case "Voir liste":
                            loggedAdmin = (LoggedAdmin) loggedUser;
                            session.setAttribute("employeesList", loggedUser.getEmployeesList(employeeSB));
                            response.sendRedirect("employeesList.jsp");
                            
                            break;
                            
                        case "Deconnexion":
                            
                            Enumeration<String> sessionsAttributeNames =  session.getAttributeNames();
                            
                            while(sessionsAttributeNames.hasMoreElements()){
                                session.removeAttribute(sessionsAttributeNames.nextElement());
                            }
                            
                            response.sendRedirect("logout.jsp");
                            
                            break;
                        default:
                            out.println("[" + action + "]");
                    }
                }
            }
        }
    }
    
    private boolean verifyInputForm(Employees e){
        if(e.getLastname() != null &&
            e.getFirstname() != null &&
            e.getHomephone() != null &&
            e.getMobilephone() != null &&
            e.getWorkphone() != null &&
            e.getAddress() != null &&
            e.getZipcode() != null &&
            e.getCity() != null &&
            e.getMail() != null)
        {
            if(contentJustNumber(e.getHomephone()) && 
                    contentJustNumber(e.getMobilephone()) &&
                    contentJustNumber(e.getWorkphone()) &&
                    contentJustNumber(e.getZipcode()) &&
                    e.getZipcode().length() < 6 &&
                    e.getMail().contains("@") &&
                    e.getMail().contains("."))
            {
                return true;
            }
        }
        return false;
    }
    
    private boolean contentJustNumber(String s){
        return Pattern.matches("[0-9 ]*",s);
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
