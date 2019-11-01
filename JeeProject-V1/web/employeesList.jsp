<%-- 
    Document   : employeesList
    Created on : Oct 24, 2019, 12:21:33 PM
    Author     : nox
--%>

<%@page import="java.util.List"%>
<%@page import="lsi.m1.models.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employees List</title>
    </head>
    <body>

        <c:set var="buttonValue" value="" scope="session" />
        <c:if test="${loggedUser == null}">
            <c:redirect url = "accueil.jsp"/>
        </c:if>

        <%
            DBActions db = new DBActions();
            session.setAttribute("employeeList", db.getEmployees());
        %>

        <c:choose>
            <c:when test = "${employeeList == null || employeeList.size() == 0}">
                Nous devons recruter !
            </c:when>
            <c:otherwise>
                <form action = "Controller">
                    <table>
                        <thead>
                            <tr>
                                <th>SEL</th>
                                <th>NOM</th>
                                <th>PRENOM</th>
                                <th>TEL DOMICILE</th>
                                <th>TEL PORTABLE</th>
                                <th>TEL PRO</th>
                                <th>ADRESSE</th>
                                <th>CODE POSTAL</th>
                                <th>VILLE</th>
                                <th>EMAIL</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${employeeList}" var="employee">
                                <tr>
                                    <td><input type = "radio" name = "selector" value = "${employee.getId()}"></td>
                                    <td>${employee.getLastName()}</td>
                                    <td>${employee.getLastName()}</td>
                                    <td>${employee.getFirstName()}</td>
                                    <td>${employee.getHomePhone()}</td>
                                    <td>${employee.getMobilePhone()}</td>
                                    <td>${employee.getWorkPhone()}</td>
                                    <td>${employee.getAddress()}</td>
                                    <td>${employee.getZipCode()}</td>
                                    <td>${employee.getCity()}</td>
                                    <td>${employee.getMail()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${loggedUser.getAccessLevel() == 'admin'}">
                        <input type = "submit" name = "button" value = "Supprimer">
                        <input type = "submit" name = "button" value = "Details">
                        <input type = "submit" name = "button" value = "Ajouter">
                    </c:if>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>
