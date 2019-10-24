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

            int id;
            String action;
            DBActions db;

            action = request.getParameter("button");

            session = request.getSession();
            if (action != null) {
                db = new DBActions();
                id = Integer.parseInt(request.getParameter("selector") != null ? request.getParameter("selector") : "-1");
                LoggedAdmin loggedAdmin;
                loggedAdmin = (LoggedAdmin) session.getAttribute("loggedUser");
                   
                session.setAttribute("deleteStatus", "");
                
                switch (action) {
                    case "Supprimer":
                        
                        if(loggedAdmin.deleteEmployee(id, db) > 0){
                            session.setAttribute("deleteStatus","Suppression réussie");
                        }
                        else{
                            session.setAttribute("deleteStatus","Veuillez sélectionner un employé.");
                        }
                        
                        response.sendRedirect("employeesList.jsp");
                        break;
                    case "Details":
                        session.setAttribute("buttonValue", "Modifier");
                        response.sendRedirect("detailsEmployee.jsp");
                        break;
                    case "Ajouter":
                        session.setAttribute("buttonValue", "Ajouter");
                        response.sendRedirect("detailsEmployee.jsp");
                        break;
                }
            } else {
                String login;
                String password;
                boolean isConnected;
                isConnected = false;
                login = request.getParameter(FRM_LOGIN);
                password = request.getParameter(FRM_PASSWORD);

                if (login.equals(getServletContext().getInitParameter("loginAdmin")) && password.equals(getServletContext().getInitParameter("passwordAdmin"))) {
                    session.setAttribute("loggedUser", new LoggedAdmin());
                    session.setAttribute("errKey", "");
                    isConnected = true;
                } else if (login.equals(getServletContext().getInitParameter("loginEmployee")) && password.equals(getServletContext().getInitParameter("passwordEmployee"))) {
                    session.setAttribute("loggedUser", new LoggedEmployee());
                    session.setAttribute("errKey", "");
                    isConnected = true;
                } else {
                    session.setAttribute("loginLevel", "");
                    session.setAttribute("errKey", "Erreur d'identification.");
                }

                if (isConnected) {
                    response.sendRedirect("employeesList.jsp");
                } else {
                    response.sendRedirect("accueil.jsp");
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
