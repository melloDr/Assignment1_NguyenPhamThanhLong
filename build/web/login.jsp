<%-- 
    Document   : login
    Created on : Jan 5, 2021, 1:42:20 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <h1>${requestScope.MSGUSERCART}</h1>
        <form action="MainController" method="POST">
            <input type="submit" name="btnAction" value="home">
        </form>
        <form action="MainController" method="POST">
            User ID:<input type="text" name="txtID" value=""/><br/>
            Password:<input type="password" name="txtPass" value=""/><br/>
            <input type="submit" value="Login" name="btnAction"/>
            <input type="reset" value="Reset"/> 
            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/Assignment1_NguyenPhamThanhLong/LoginGoogleServlet&response_type=code
               &client_id=1088851091302-v580sppucg62mohj1amqjrt5pck5tg7f.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>  
        </form>

        <h1>${sessionScope.NOTIFY}</h1>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    </body>
</html>
