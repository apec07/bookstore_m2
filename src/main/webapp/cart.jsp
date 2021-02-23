<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="idv.cm.db.BookVO" %>
<%@ page import="java.util.*" %>
<%
	List<BookVO> list = (List<BookVO>) session.getAttribute("shoppingcart");
	if(list==null || list.size()==0){
		return;
	}
%>

<html>
<head>
<meta charset="utf-8">
<title>Book Cart</title>
</head>
<body>

	<h2>Book Cart</h2>
	<table border="1">
		<tr>
			<th width="200">Name</th><th width="100">Author</th><th width="100">Publisher</th><th width="100">Price</th>
			<th width="120">Quantity</th><th width="120"><img src="images/shopping-cart.png" width="45px" height="35px"></th>
		</tr>
	</table>
	
	<table border="1">
		<c:forEach var="cart" items="${shoppingcart}" varStatus="s">
		<tr>
			<td width="200"><div align="center">${cart.name}</div></td>
			<td width="100"><div align="center">${cart.author}</div></td>
			<td width="100"><div align="center">${cart.publisher}</div></td>
			<td width="100"><div align="center">${cart.price}</div></td>
			<td width="120"><div align="center">${cart.quantity}</div></td>
			<td width="120">
			<form name="deleteForm" action="shopping.html" method="post">
				 <input type="hidden" name="action"  value="DEL">
              	 <input type="hidden" name="delIndex" value="${s.index}">
				<div align="center"><input type="submit" value="Delete"/></div>
			</form>
			</td>
		</tr>
		</c:forEach>
	</table>
	<hr>
	<form name="checkoutForm" action="shopping.html" method="post">
		<input type="hidden" name="action" value="CHECKOUT"/>
		<input type="submit" value="CheckOut" class="button"/>
	</form>

</body>
</html>