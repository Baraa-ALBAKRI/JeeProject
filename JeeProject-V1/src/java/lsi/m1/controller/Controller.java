/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lsi.m1.models.DBActions;
import lsi.m1.models.EmployeeBean;
import lsi.m1.models.LoggedAdmin;
import lsi.m1.models.LoggedEmployee;
import static lsi.m1.utils.Constants.*;

/**
 *
 * @author nox
 */
public class Controller extends HttpServlet {

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
            DBActions db = new DBActions();
            LoggedEmployee loggedUser = (LoggedAdmin) session.getAttribute("loggedUser");
            if (loggedUser == null) {
                String login = request.getParameter(FRM_LOGIN);
                String password = request.getParameter(FRM_PASSWORD);
                if (login == null && password == null) {
                    response.sendRedirect("accueil.jsp");
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
                    response.sendRedirect("accueil.jsp");
                } else {
                    session.setAttribute("employeesList", loggedUser.getEmployeesList(db));
                    response.sendRedirect("employeesList.jsp");
                }
            } //Not acceil page
            else {
                String action = request.getParameter("button");
                if (action != null) {
                    int id = Integer.parseInt(request.getParameter("selector") != null ? request.getParameter("selector") : "-1");
                    LoggedAdmin loggedAdmin = (LoggedAdmin) loggedUser;
                    session.setAttribute("selectStatus", "");

                    switch (action) {
                        case "Supprimer":

                            if (loggedAdmin.deleteEmployee(id, db) > 0) {
                                session.setAttribute("selectStatus", "Suppression réussie.");
                            } else {
                                session.setAttribute("selectStatus", "Veuillez sélectionner un employé.");
                            }
                            session.setAttribute("employeesList", loggedUser.getEmployeesList(db));
                            response.sendRedirect("employeesList.jsp");
                            break;
                        case "Details":
                            if (id > -1) {

                                session.setAttribute("buttonValue", "Modifier");
                                session.setAttribute("employe", loggedAdmin.getEmployee(id, db));

                                response.sendRedirect("detailsEmployee.jsp");
                            } else {
                                session.setAttribute("selectStatus", "Veuillez sélectionner un employé.");
                                session.setAttribute("employeesList", loggedUser.getEmployeesList(db));
                                response.sendRedirect("employeesList.jsp");
                            }

                            break;
                        case "Ajouter":
                            if (session.getAttribute("buttonValue").equals("Ajouter")) {
                                EmployeeBean e = new EmployeeBean();

                                e.setLastName(request.getParameter("lastNameInput"));
                                e.setFirstName(request.getParameter("firstNameInput"));
                                e.setHomePhone(request.getParameter("homePhoneInput"));
                                e.setMobilePhone(request.getParameter("mobilePhoneInput"));
                                e.setWorkPhone(request.getParameter("workPhoneInput"));
                                e.setAddress(request.getParameter("addressInput"));
                                e.setZipCode(request.getParameter("zipInput"));
                                e.setCity(request.getParameter("cityInput"));
                                e.setMail(request.getParameter("mailInput"));

                                loggedAdmin.addEmployee(e, db);
                                session.setAttribute("employeesList", loggedUser.getEmployeesList(db));
                                response.sendRedirect("employeesList.jsp");
                            } else {
                                session.setAttribute("buttonValue", "Ajouter");
                                session.setAttribute("employe", null);
                                response.sendRedirect("detailsEmployee.jsp");
                            }

                            break;
                        case "Modifier":
                            EmployeeBean e = new EmployeeBean();
                            EmployeeBean oldE = (EmployeeBean) session.getAttribute("employe");
                            e.setId(oldE.getId());
                            e.setLastName(request.getParameter("lastNameInput"));
                            e.setFirstName(request.getParameter("firstNameInput"));
                            e.setHomePhone(request.getParameter("homePhoneInput"));
                            e.setMobilePhone(request.getParameter("mobilePhoneInput"));
                            e.setWorkPhone(request.getParameter("workPhoneInput"));
                            e.setAddress(request.getParameter("addressInput"));
                            e.setZipCode(request.getParameter("zipInput"));
                            e.setCity(request.getParameter("cityInput"));
                            e.setMail(request.getParameter("mailInput"));
                            loggedAdmin.modifyEmployee(e, db);
                            session.setAttribute("employeesList", loggedUser.getEmployeesList(db));
                            response.sendRedirect("employeesList.jsp");
                            break;
                        case "Voir liste":
                            session.setAttribute("employeesList", loggedUser.getEmployeesList(db));
                            response.sendRedirect("employeesList.jsp");
                            break;
                        case "Deconnexion":
                            response.sendRedirect("employeesList.jsp");
                            response.sendRedirect("logout.jsp");
                            break;
                        default:
                            out.println("[" + action + "]");
                    }
                }
            }
        }
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
