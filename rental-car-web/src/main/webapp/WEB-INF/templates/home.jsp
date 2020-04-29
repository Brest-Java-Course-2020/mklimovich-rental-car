<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Home Page</title>
        <link href="<c:url value='/static/css/bootstrap2.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/static/css/custom.css' />" rel="stylesheet"></link>
    </head>
    <body>
        <div class="form-container">
            <div class="form-horizontal">
                <h2>Hello, friend!</h2>

                <h3>Please, sign in</h3>
                <a href="${pageContext.request.contextPath}/mainPage">Go to main page</a> 
            </div>
        </div>
    </body>
</html>