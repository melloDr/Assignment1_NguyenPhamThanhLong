<%-- 
Document   : historyDetail
Created on : Jan 19, 2021, 5:31:28 PM
Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <c:set var="list" value="${sessionScope.LISTHISTORY}"></c:set> 
        <c:set var="countt" value="${0}"></c:set> 
        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>order Detail Id</th>
                            <th>food Id</th>
                            <th>quantity</th>
                            <th>price</th>
                            <th>id</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="f" items="${list}" varStatus="countt">
                            <tr>
                                <td>${countt.count}</td>
                                <td>${f.orderDetailId}</td>
                                <td>${f.foodId}</td>
                                <td>${f.quantity}</td>
                                <td>${f.price}</td>
                                <td>${f.id}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>
        <a href="home.jsp" >Home</a>
    </body>
</html>
