<%-- 
    Document   : index
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Index - Connect</title>
        <link rel="stylesheet" href="public/css/signin.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        
            <form name="myFrm" action="Controller" class="form-signin">

            <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
            <label for="inputName" class="sr-only">Name</label>
            <input type="text" id="inputName" class="form-control" name = "loginForm" placeholder="Name" required autofocus>
            <br>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" name = "passwordForm" placeholder="Password" required>
            
            <button class="btn btn-lg btn-primary btn-block" type="submit">Connect</button>
            <c:if  test="${!empty errKey}">
                <span style="color:red">  ${ errKey} </span><br/>
            </c:if>
            <p class="mt-5 mb-3 text-muted">&copy; 2019-2020</p>
        </form>
        
        
    </body>
</html>
