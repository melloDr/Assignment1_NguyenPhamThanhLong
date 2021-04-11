<%-- 
    Document   : admin
    Created on : Jan 8, 2021, 1:45:43 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <form action="MainController">
            <input type="submit" name="btnAction" value="Logout">
        </form>
        <a href="createFood.jsp">Add new</a>
        <c:set var="list" value="${requestScope.LIST}"></c:set> 
        <c:set var="END" value="${sessionScope.END}"></c:set> 
        <c:set var="countt" value="${requestScope.COUNT_NO * 5 - 5}"></c:set> 
        <c:set var="s" value="${sessionScope.cbbCategories}"></c:set>
        <c:set var="result" value="${fn:split(s, '-')}" ></c:set>
        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Images</th>
                            <th>Date of create</th>
                            <th>Were deleted</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="MainController" >
                        <c:forEach var="f" items="${list}" varStatus="countt">
                            <tr>
                                <td>${countt.count}</td>
                                <td>
                                    <input type="text" name="txtIdToUpdate" value="${f.id}">
                                </td>
                                <td>
                                    <input type="text" name="txtNameToUpdate" value="${f.name}">
                                </td>
                                <td>
                                    <input type="text" name="txtQuantityToUpdate" value="${f.quantity}">
                                </td>
                                <td>
                                    <input type="text" name="txtPriceToUpdate" value="${f.price}">
                                </td>
                                <td>
                                    <select name="txtCategoryToUpdate">
                                        <c:forEach var="i" items="${result}">
                                            <c:if test="${i.trim().equals(f.category.trim())}">
                                                <option  value="${i}" selected="">${i}</option>
                                            </c:if>
                                            <c:if test="${!i.trim().equals(f.category.trim())}">
                                                <option  value="${i}" >${i}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>Show image here</td>
                                <td>${f.date}</td>
                                <td>
                                    <input type="checkbox" name="checkBoxDelete" value="${f.id}" 
                                           <c:if test="${0 == f.status}">
                                               checked
                                           </c:if>
                                </td> 

                                <td>
                                    <input type="submit" name="btnAction" value="Update"/>
                                </td>
                            </tr>      
                        </c:forEach>
                        <input type="submit" name="btnAction" value="Delete or reset" onclick="return confirm('Are you sure want to Remove?');">
                    </form>
                </tbody>
            </table>
        </c:if>
    </c:if>
    <form action="MainController">
        <c:forEach var="i" begin="0" end="${END}">
            <input type="submit" name="btnPage" value="${i+1}">    
        </c:forEach>
        <c:set var="flag" scope="session" value="admin"></c:set>
    </form>

    <!--<a href="MainController?btnAction=DeleteAsAdmin" onclick="return confirm('Are you sure you want to Remove?');">Delete</a>-->
</body>
</html>
