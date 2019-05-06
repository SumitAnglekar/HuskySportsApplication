<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false" %>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Welcome</title>
    </head>
    <body>
        <table>
            <tr>
                <td>Welcome ${requestScope.customer.firstName}</td>
            </tr>
            <tr>
            </tr>
            <tr>
                <td><a href="${pageContext.request.contextPath}/soccer/apply.htm">Fill in the form to register and get a chance to play for the team !</a></td>
            </tr>
            <tr>
                <td><a href="/SportsManagement">Home</a>
                </td>
            </tr>
        </table>
    </body>
    </html>