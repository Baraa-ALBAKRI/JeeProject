/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.v2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            
            LoggedAdmin loggedAdmin = new LoggedAdmin();
           
                Employees e =  loggedAdmin.getEmployee(2, employeeSB);
                e.setFirstname("Baraaa");
                loggedAdmin.addEmployee(e, employeeSB);
                e =  loggedAdmin.getEmployee(8, employeeSB);
                request.getSession().setAttribute("key_User", e);
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
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
