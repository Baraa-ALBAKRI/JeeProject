<%--
    Document   : detailsEmployee
    Created on : Oct 24, 2019, 5:15:43 PM
    Author     : nox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Modify</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

    </head>
    <body>
        <form action =  "Controller">
            <div class="form-group col-md-8">
                <label for="lastNameInput">Nom</label>
                <input type="text" class="form-control" name="lastNameInput">
            </div>
            <div class="form-group col-md-8">
                <label for="firstNameInput">Prénom</label>
                <input type="text" class="form-control" name="firstNameInput">
            </div>
            <div class="form-group col-md-8">
                <label for="homePhoneInput">Tel dom</label>
                <input type="tel" class="form-control" name="homePhoneInput">
            </div>
            <div class="form-group col-md-8">
                <label for="mobilePhoneInput">Tel mob</label>
                <input type="tel" class="form-control" name="mobilePhoneInput">
            </div>
            <div class="form-group col-md-8">
                <label for="workPhoneInput">Tel pro</label>
                <input type="tel" class="form-control" name="workPhoneInput">
            </div>
            <div class="form-row" style="margin-left: 0.5%;">
                <div class="form-group col-md-4">
                    <label for="addressInput">Adresse</label>
                    <input type="text" class="form-control" name="addressInput">
                </div>
                <div class="form-group col-md-4">
                    <label for="zipInput">Code Postal</label>
                    <input type="text" class="form-control" name="zipInput">
                </div>
            </div>
            <div class="form-row" style="margin-left: 0.5%;">
                <div class="form-group col-md-4">
                    <label for="cityInput">Ville</label>
                    <input type="text" class="form-control" name="cityInput">
                </div>
                <div class="form-group col-md-4">
                    <label for="mailInput">Adresse mail</label>
                    <input type="mail" class="form-control" name="mailInput">
                </div>
            </div>
            <div style="margin-left: 56%">
                <%
            String buttonValue = (String)session.getAttribute("buttonValue");
            if(buttonValue != null)
            {
                out.println("<input type=\"submit\" class=\"btn btn-primary\" name = \"button\" value = \"" + buttonValue + "\">");
            }
            %>
                <input type="submit" class="btn" name = "button" value = "Voir liste">
            </div>
        </form>
            <%out.println(session.getAttribute("employe"));%>
    </body>
</html>