<%-- 
    Document   : viewCart
    Created on : Jan 15, 2021, 12:31:22 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dtos.FoodDto"%>
<%@page import="java.util.Map"%>
<%@page import="dtos.CartDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>

        <c:set var="total" value="${0}"></c:set> 
        <c:set var="msgQuantity" value="${requestScope.MSGSTRINGOVER}"></c:set> 
        <c:if test="${msgQuantity != null}">
            ${msgQuantity}
        </c:if>
        <c:set var="cart" value="${sessionScope.CART}"></c:set>
        <c:if test="${cart != null}">
            <c:if test="${not empty cart}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Total money of this price</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="MainController">
                        <c:set var="countt" value="${1}"></c:set> 
                        <c:set var="list" value="${cart.getCart().values()}"></c:set>
                        <c:forEach var="f" items="${list}" varStatus="countt">
                            <c:set var="total" value="${total = total + f.price*f.quantity}"></c:set>

                                <tr>
                                    <td>${countt.count}</td>
                                <td>${f.name}</td>
                                <td>
                                    <input type="text" name="txtQuantityUpdateMember" value="${f.quantity}">
                                </td>
                                <td>${f.price}</td>
                                <td>${f.category}</td>
                                <td>
                                    ${f.quantity * f.price}
                                    <input type="hidden" name="txtQuantity" value="${f.quantity}"
                                </td>
                                <td>
                                    <input type="submit" name="btnAction" value="Delete food of Cart" onclick="return confirm('Are you sure want to Remove?');">
                                    <input type="hidden" name="txtFoodID" value="${f.id}">
                                </td> 
                                <td>
                                    <input type="submit" name="btnAction" value="Update your food">
                                    <input type="hidden" name="txtFoodID" value="${f.id}">
                                </td> 
                            </tr>        
                        </c:forEach>
                        <c:set var="TOTAL" scope="session" value="${total}"></c:set>
                            <input type="submit" name="btnAction" value="Confirm" > <h2>Confirm to ship COD</h2>
                        </form>
                    </tbody>
                </table>

                <h1>Total: ${total}</h1>
            <form action="authorize_payment" method="post">
                <table>
                    <tr>
                        <td></td>
                        <td>
                            <input type="hidden" name="product" value="Next iPhone" />
                        </td>
                    </tr>
                    <tr>
                        <td>Sub Total:</td>
                        <td> <%=5%>
                            <input type="hidden" name="subtotal" value="5" />
                        </td>
                    </tr>
                    <tr>
                        <td>Shipping:</td>
                        <td><%=1%>
                            <input type="hidden" name="shipping" value="1" />
                        </td>
                    </tr>		
                    <tr>
                        <td>Tax:</td>
                        <td>
                            <%=1%>
                            <input type="hidden" name="tax" value="1" />
                        </td>
                    </tr>		
                    <tr>
                        <td>Total Amount:</td>
                        <td>${total + 7}
                            <input type="hidden" name="total" value="${total + 7}" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Checkout" /> <h2>Checkout to use paypal</h2>
                        </td>
                    </tr>
                </table>
            </form>
        </c:if>
    </c:if>
    <a href="MainController?btnAction=AddMore">Add more?</a>
</body>
</html>
