<%-- 
Document   : home
Created on : Jan 5, 2021, 1:42:29 PM
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
        <title>Home Page</title>
    </head>
    <h1> HELLO: ${sessionScope.LOGIN_NAME}</h1>
    <body>
        <a href="login.jsp">login</a>
        <form action="MainController">
            <input type="submit" name="btnAction" value="Logout"/><br/>
            <input type="submit" name="btnAction" value="View Cart">
        </form>
        <h1>${sessionScope.MESSAGE}</h1>
        <c:set var="END" value="${sessionScope.END}"></c:set> 

        <c:set var="s" value="${sessionScope.cbbCategories}"></c:set>
        <c:set var="result" value="${fn:split(s, '-')}" ></c:set>
            <form action="MainController">
                <input type="submit" name="btnAction" value="Get All Foods"/><br/>
                Search: <br/>
                Name: <input type="text" name="txtSearchByName" placeholder="Input to search">||
                Price from<input type="text" name="txtPriceFrom"> 
                to<input type="text" name="txtPriceTo">||
                Categories:
                <select name="txtSearchByCategory">
                <c:forEach var="i" items="${result}">
                    <option  value="${i}">${i}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="index" value="1"/>
            <input type="submit" name="btnAction" value="Search"/>
        </form>
        <c:set var="list" value="${requestScope.LIST}"></c:set> 
        <c:set var="countt" value="${requestScope.COUNT_NO * 5 - 5}"></c:set> 

        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Images</th>
                            <th>Date of create</th>
                            <th>Add To Cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="f" items="${list}" varStatus="countt">
                        <form action="MainController">
                            <tr>
                                <td>${countt.count}</td>
                                <td>${f.name}</td>
                                <td>${f.quantity}</td>
                                <td>${f.price}</td>
                                <td>${f.category}</td>
                                <td>
                                    <img src="/Assignment1_NguyenPhamThanhnLong/web/images/cola.jpg" />
                                </td>
                                <td>${f.date}</td>
                                <td>
                                    <input type="submit" name="btnAction" value="Add this food">
                                    <input type="hidden" name="txtIdToCart" value="${f.id}">
                                    <input type="hidden" name="txtNameToCart" value="${f.name}">
                                    <input type="hidden" name="txtQuantityToCart" value="${f.quantity}">
                                    <input type="hidden" name="txtPriceToCart" value="${f.price}">
                                    <input type="hidden" name="txtCategoryToCart" value="${f.category}">
                                    <input type="hidden" name="txtDateToCart" value="${f.date}">
                                </td>
                            </tr>

                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>

    <c:if test="${empty list}">

        <h2>Sorry we don't have the thing, which you're finding!</h2>
    </c:if>
    <form action="MainController">
        <c:forEach var="i" begin="0" end="${END}">
            <input type="submit" name="btnPage" value="${i+1}">    
        </c:forEach>
        <c:set var="flag" scope="session" value="getAll"></c:set>
        <% // session.setAttribute("flag", "getAll");%>
    </form>
    <form action="MainController">
        <input type="submit" name="btnAction" value="Shopping history">
    </form>
</body>
</html>
