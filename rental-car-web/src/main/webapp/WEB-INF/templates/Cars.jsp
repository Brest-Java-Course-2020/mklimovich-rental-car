<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Cars</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <a href="${pageContext.request.contextPath}/addCar">Add Car</a>
        <br>
        <h3>List of all cars</h3>
        ${message}
        
        <table border="1px" cellpadding="0" cellspacing="0">
            <thead>
                <tr>
                    <th>Brand Car</th>
                    <th>Engine Volume</th>
                    <th>Color</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="cars" items="${carsList}">
                    <tr>
                        <td>${cars.brandCar}</td>
                        <td>${cars.engineVolume}</td>
                        <td>${cars.color}</td>
                        <td><a href="${pageContext.request.contextPath}/editCars/${cars.id}">Edit</a></td>
                        <td><a href="${pageContext.request.contextPath}/deleteCars/${cars.id}">Delete</a></td>
                        
                    </tr>
                </c:forEach>
            </tbody>


        </table>
        <br>
        Go back to <a href="${pageContext.request.contextPath}/mainPage"> main Page</a>
    </body>
</html>