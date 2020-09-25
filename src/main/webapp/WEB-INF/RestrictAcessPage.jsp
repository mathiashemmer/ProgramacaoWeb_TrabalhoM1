<%-- 
    Document   : RestrictAcessPage
    Created on : Sep 22, 2020, 1:01:05 PM
    Author     : Mathias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SSA</title>
    </head>
    <body style="margin: 0px; padding:0px;">
        <div style="width: 500px;height: 230px;background-color: antiquewhite;border-radius: 5px;box-shadow: 0px 5px 5px 1px #00000050;padding: 10px;margin: auto; text-align:center; margin-top:200px;">
            <h1>Welcome Agent:</h1>
            <form action="" method="POST">
                    <p><%out.print((String)session.getAttribute("username"));%></p>
                    <br/>
                <button type="submit">Logout</button>
                    <br />
            </form>
        </div>
    </body>
</html>
