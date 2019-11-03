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
        
        
        <span>
            <form action = "Controller">
                <span style = "color: blue;">Bonjour ${loggedUser.getAccessLevel()} ! Votre session est active.</span>
                <button type="submit" name ="button" class="btn btn-default btn-sm" value ="Deconnexion" style="float: right;">
                    <span class="glyphicon glyphicon-off" style = "color: red;"></span> 
                </button>
            </form>
        </span>
                <br>
        
        
        <form action =  "Controller">
            <div class="form-group col-md-8">
                <label for="lastNameInput">Nom</label>
                <input type="text" class="form-control" name="lastNameInput" value ="${employe.lastName}">
            </div>
            <div class="form-group col-md-8">
                <label for="firstNameInput">Prénom</label>
                <input type="text" class="form-control" name="firstNameInput" value ="${employe.firstName}">
            </div>
            <div class="form-group col-md-8">
                <label for="homePhoneInput">Tel dom</label>
                <input type="tel" class="form-control" name="homePhoneInput" value="${employe.homePhone}">
            </div>
            <div class="form-group col-md-8">
                <label for="mobilePhoneInput">Tel mob</label>
                <input type="tel" class="form-control" name="mobilePhoneInput" value="${employe.mobilePhone}">
            </div>
            <div class="form-group col-md-8">
                <label for="workPhoneInput">Tel pro</label>
                <input type="tel" class="form-control" name="workPhoneInput" value="${employe.workPhone}">
            </div>
            <div class="form-row" style="margin-left: 0.5%;">
                <div class="form-group col-md-4">
                    <label for="addressInput">Adresse</label>
                    <input type="text" class="form-control" name="addressInput" value="${employe.address}">
                </div>
                <div class="form-group col-md-4">
                    <label for="zipInput">Code Postal</label>
                    <input type="text" class="form-control" name="zipInput" value="${employe.zipCode}">
                </div>
            </div>
            <div class="form-row" style="margin-left: 0.5%;">
                <div class="form-group col-md-4">
                    <label for="cityInput">Ville</label>
                    <input type="text" class="form-control" name="cityInput" value="${employe.city}">
                </div>
                <div class="form-group col-md-4">
                    <label for="mailInput">Adresse mail</label>
                    <input type="mail" class="form-control" name="mailInput" value="${employe.mail}">
                </div>
            </div>
            <div style="margin-left: 56%">
                <input type="submit" class="btn btn-primary" name = "button" value = "${buttonValue}">
                <input type="submit" class="btn" name = "button" value = "Voir liste">
            </div>
        </form>
           
    </body>
</html>