<%-- 
    Document   : createFood
    Created on : Jan 15, 2021, 11:52:49 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Food Page</title>
    </head>
    <body>
        <c:set var="s" value="${sessionScope.cbbCategories}"></c:set>
        <c:set var="result" value="${fn:split(s, '-')}" ></c:set>
        <c:set var="msgIdAdd" value="${requestScope.MSGIDADD}"></c:set> 
        <c:set var="msgQuantityAdd" value="${requestScope.MSGQUANTITYADD}"></c:set> 
        <c:set var="msgPriceAdd" value="${requestScope.MSGPRICEADD}"></c:set> 
        <c:set var="txtName" value="${requestScope.NAMETOCREATESESSION}"></c:set> 
        <c:set var="txtId" value="${requestScope.IDTOCREATESESSION}"></c:set> 
        <c:set var="txtQuantity" value="${requestScope.QUANTITYTOCREATESESSION}"></c:set> 
        <c:set var="txtPrice" value="${requestScope.PRICETOCREATESESSION}"></c:set> 
        
        <form action="MainController">
            <h2>To create a food, please input some information: </h2>
            Food ID: <input type="text" name="txtFoodIdToCreate" value="${txtId}"><h3>${msgIdAdd}</h3>
            Name: <input type="text" name="txtNameToCreate" value="${txtName}"><br/>
            Quantity: <input type="text" name="txtQuantityToCreate" value="${txtQuantity}" placeholder="Quantity must be positive number"><h3>${msgQuantityAdd}</h3>
            Price <input type="text" name="txtPriceToCreate" value="${txtPrice}" placeholder="Price must be positive number"><h3>${msgPriceAdd}</h3><
            Category: 
            <select name="txtCategoryToCreate">
                 <c:forEach var="i" items="${result}">
                    <option  value="${i}">${i}</option>
                </c:forEach>
            </select><br/>
            Images: <input type="text" name="txtImagesToCreate" placeholder="Upload food's images"><br/>
            <input type="submit" name="btnAction" value="Add new">
        </form>
    </body>
</html>
