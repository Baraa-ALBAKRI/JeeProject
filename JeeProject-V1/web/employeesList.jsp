<%-- 
    Document   : employeesList
    Created on : Oct 24, 2019, 12:21:33 PM
    Author     : nox
--%>

<%@page import="java.util.List"%>
<%@page import="lsi.m1.models.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employees List</title>
    </head>
    <body>
        <%
            LoggedEmployee loggedUser = (LoggedEmployee)session.getAttribute("loggedUser");
            List<EmployeeBean> employeesList;
            DBActions db;
            
            db = new DBActions();
            
            if(loggedUser == null){
                response.sendRedirect("accueil.jsp");
            }
            
            employeesList = loggedUser.getEmployeesList(db);
            
            if(employeesList != null && employeesList.size()>0){
                out.println(session.getAttribute("deleteStatus") != null ? session.getAttribute("deleteStatus") : "");
                
                out.println("<form action = \"Controller\">");
                    out.println("<table>");
                        out.println("<thead>");
                            out.println("<tr>");
                                out.println("<th>");
                                out.println("Sél");
                                out.println("</th>");

                                out.println("<th>");
                                out.println("NOM");
                                out.println("</th>");

                                out.println("<th>");
                                out.println("PRENOM");
                                out.println("</th>");

                                out.println("<th>");
                                out.println("TEL DOMICILE");
                                out.println("</th>");

                                out.println("<th>");
                                out.println("TEL PORTABLE");
                                out.println("</th>");

                                out.println("<th>");
                                out.println("TEL PRO");
                                out.println("</th>");

                                out.println("<th>");
                                out.println("ADRESSE");
                                out.println("</th>");

                                out.println("<th>");
                                out.println("CODE POSTAL");
                                out.println("</th>");

                                out.println("<th>");
                                out.println("VILLE");
                                out.println("</th>");

                                out.println("<th>");
                                out.println("EMAIL");
                                out.println("</th>");
                            out.println("</tr>");
                        out.println("</thead>");

                        out.println("<tbody>");
                        for(EmployeeBean e : employeesList){
                            out.println("<tr>");
                                out.println("<td>");
                                out.println("<input type = \"radio\" name = \"selector\" required value = \"" + e.getId()+"\">");
                                out.println("</td>");

                                out.println("<td>");
                                out.println(e.getLastName());
                                out.println("</td>");

                                out.println("<td>");
                                out.println(e.getFirstName());
                                out.println("</td>");

                                out.println("<td>");
                                out.println(e.getHomePhone());
                                out.println("</td>");

                                out.println("<td>");
                                out.println(e.getMobilePhone());
                                out.println("</td>");

                                out.println("<td>");
                                out.println(e.getWorkPhone());
                                out.println("</td>");

                                out.println("<td>");
                                out.println(e.getAddress());
                                out.println("</td>");

                                out.println("<td>");
                                out.println(e.getZipCode());
                                out.println("</td>");

                                out.println("<td>");
                                out.println(e.getCity());
                                out.println("</td>");

                                out.println("<td>");
                                out.println(e.getMail());
                                out.println("</td>");
                            out.println("</tr>");
                        }
                        out.println("</tbody>");

                    out.println("</table>");
             
                   
                    if(loggedUser.getAccessLevel().equals("admin")){
                         out.println("<input type = \"submit\" name = \"button\" value = \"Supprimer\">");
                         out.println("<input type = \"submit\" name = \"button\" value = \"Details\">");
                         out.println("<input type = \"submit\" name = \"button\" value = \"Ajouter\">");
                    }
                    
                out.println("</form>");
            }
            else{
                out.println("Nous devons recruter !");
            }
        %>
    </body>
</html>
