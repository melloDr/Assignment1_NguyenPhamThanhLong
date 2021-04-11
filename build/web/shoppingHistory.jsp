<%-- 
    Document   : shoppingHistory
    Created on : Jan 17, 2021, 11:34:10 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping history Page</title>
    </head>
    <body>

        <form action="MainController">
            Search: <br/>
            <input type="text" name="txtSearchHis" placeholder="Date: yyyy-MM-dd"><br/>
            <select name="txtSearchHistoryBy">
                <option value="txtSearchByNameHis">Search By Name</option>
                <option value="txtDateHis">Search By Date</option>
            </select>
            <input type="submit" name="btnAction" value="Find"/>
        </form>
        <c:set var="list" value="${sessionScope.LISTHISTORY}"></c:set> 
        <c:set var="countt" value="${0}"></c:set> 
        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Count</th>
                            <th>user ID</th>
                            <th>total</th>
                            <th>date buy</th>
                            <th>View</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="MainController">
                        <c:forEach var="f" items="${list}" varStatus="countt">
                            <tr>
                                <td>${countt.count}</td>
                                <td>${f.userID}</td>
                                <td>${f.total}</td>
                                <td>${f.dateBuy}</td>
                                <td><input type="submit" name="btnAction" value="View history"></td>
                                <td><input type="hidden" name="txtIdHistory" value="${f.userID}"></td>
                            </tr>
                        </c:forEach>
                    </form>
                </tbody>
            </table>
        </c:if>
    </c:if>
</body>
</html>
