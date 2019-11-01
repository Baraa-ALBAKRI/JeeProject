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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <style>
            td{
                border:1px solid #000;
            }

            tr td:last-child{
                width:1%;
                white-space:nowrap;
            }
        </style>
    </head>
    <body>
        <div style="padding: 15px;">
        <c:set var="buttonValue" value="" scope="session" />
        <c:if test="${loggedUser == null}">
            <c:redirect url = "accueil.jsp"/>
        </c:if>

        <span>
            Bonjour ${loggedUser.getAccessLevel()} ! Votre session est active.
            <a href="logout.jsp" class="btn btn-info btn-lg">
              <span class="glyphicon glyphicon-off"></span> Déconnexion 
            </a>
        </span>
        
        <c:choose>
            <c:when test="${selectStatus != null && selectStatus == 'Suppression réussie.'}">
                <span>Suppression réussie.</span>
            </c:when>
            <c:when test="${selectStatus != null && selectStatus == 'Veuillez sélectionner un employé.'}">
                <span>Veuillez sélectionner un employé.</span>
            </c:when>
        </c:choose>
        
        

        <c:choose>
            <c:when test = "${employeesList == null || employeesList.size() == 0}">
                Nous devons recruter !
            </c:when>
            <c:otherwise>
                <form action = "Controller">
                    <table style="width: 100%;">
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
                            <c:forEach items="${employeesList}" var="employee">
                                <tr>
                                    <td><input type = "radio" name = "selector" value = "${employee.getId()}"></td>
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
                </div>
    </body>
</html>
