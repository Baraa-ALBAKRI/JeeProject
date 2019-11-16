<%--
    Document   : detailsEmployee
    Created on : Oct 24, 2019, 5:15:43 PM
    Author     : nox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Modify</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

    </head>
    <body>
        <div style="padding: 15px;">   

            <span>
                <form action = "Controller">
                    <span style = "color: blue;">Bonjour ${loggedUser.getAccessLevel()} ! Votre session est active.</span>
                    <button type="submit" name ="button" class="btn btn-default btn-sm" value ="Deconnexion" style="float: right;">
                        <span class="glyphicon glyphicon-off" style = "color: red;"></span> 
                    </button>
                </form>
            </span>
            <br>


            <c:choose>
                <c:when test="${selectStatus != null}">
                    <span style="color:${selectStatusColor};">${selectStatus}</span>
                </c:when>
            </c:choose>


            <form action =  "Controller">
                <div class ="row">
                    <div class ="col-lg-12">
                        <label for="lastNameInput">Nom</label>
                        <input type="text" class="form-control" name="lastNameInput" value ="${employe.lastname}">
                    </div>
                </div>

                <div class ="row">
                    <div class ="col-lg-12">
                        <label for="firstNameInput">Prénom</label>
                        <input type="text" class="form-control" name="firstNameInput" value ="${employe.firstname}">
                    </div>
                </div>

                <div class ="row">
                    <div class ="col-lg-12">
                        <label for="homePhoneInput">Tel dom</label>
                        <input type="tel" class="form-control" name="homePhoneInput" value="${employe.homephone}">
                    </div>
                </div>
                <div class ="row">
                    <div class ="col-lg-12">
                        <label for="mobilePhoneInput">Tel mob</label>
                        <input type="tel" class="form-control" name="mobilePhoneInput" value="${employe.mobilephone}">
                    </div>
                </div>
                <div class ="row">
                    <div class ="col-lg-12">
                        <label for="workPhoneInput">Tel pro</label>
                        <input type="tel" class="form-control" name="workPhoneInput" value="${employe.workphone}">
                    </div>
                </div>
                <div class ="row">
                    <div class ="col-lg-6">
                        <label for="addressInput">Adresse</label>
                        <input type="text" class="form-control" name="addressInput" value="${employe.address}">
                    </div>
                    <div class ="col-lg-6">
                        <label for="zipInput">Code Postal</label>
                        <input type="text" class="form-control" name="zipInput" value="${employe.zipcode}">
                    </div>
                </div>


                <div class ="row">
                    <div class ="col-lg-6">
                        <label for="cityInput">Ville</label>
                        <input type="text" class="form-control" name="cityInput" value="${employe.city}">
                    </div>
                    <div class ="col-lg-6">
                        <label for="mailInput">Adresse mail</label>
                        <input type="mail" class="form-control" name="mailInput" value="${employe.mail}">
                    </div>
                </div>
                <div class="row">
                    <br>
                </div>
                <div class="row">
                    <div class ="col-lg-6">
                    </div>
                    <div class ="col-lg-6">
                        <div class ="col-lg-12 text-center">
                            <input type="submit" class="btn btn-primary" name = "button" value = "${buttonValue}">

                            <input type="submit" class="btn" name = "button" value = "Voir liste">
                        </div>
                    </div>
                </div>


            </form>
        </div>
    </body>
</html>