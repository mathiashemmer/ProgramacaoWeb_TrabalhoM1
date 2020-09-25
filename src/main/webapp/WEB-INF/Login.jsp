<%-- 
    Document   : Login
    Created on : Sep 22, 2020, 12:56:46 PM
    Author     : Mathias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Secret Agency</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body style="margin: 0px; padding:0px;">
        <div style="width: 500px;height: 230px;background-color: antiquewhite;border-radius: 5px;box-shadow: 0px 5px 5px 1px #00000050;padding: 10px;margin: auto; text-align:center; margin-top:200px;">
            <h1>Super Secret Agency</h1>
            <form action="" method="POST">
                <label for="username">User</label>
                    <br/>
                <input type="text" id="username" name="txtUserName">
                    <br/>
                <label for="password">Password</label>
                    <br/>
                <input type="password" id="password" name="txtPassword">
                    <br/>
                    <br/>
                <button type="submit">Login</button>
                    <br />
                <label for="keepconnected">Keep me connected</label>
                <input type="checkbox" id="keepconnected" name="keepMeIn" value="1">
            </form>
        </div>
    </body>
</html>
