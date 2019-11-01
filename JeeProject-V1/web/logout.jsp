<%-- 
    Document   : logout
    Created on : 1 nov. 2019, 13:40:32
    Author     : nicob
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Au revoir.</title>
    </head>
    <body>
        <p>Déconnexion en cours...</p>
        <p>Vous allez être redirigé(e) vers la page d'accueil.</p>
        <c:set scope="session" var="button" value="Deconnexion" />
        <c:remove var="loggedUser" scope="session" />
        <c:redirect url = "Controller"/>
    </body>
</html>
