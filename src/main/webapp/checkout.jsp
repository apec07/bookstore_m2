<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="idv.cm.db.BookVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CheckOut Confirm</title>
<style>
#ordersubmit{
  		color: blue;    
        background-color: #FFFFFF;
        border: 0px none;  //去邊框
        font-family: "宋體";
        font-size: 15px;
        text-decoration:underline;  //加下劃線
}
#ordersubmit:hover{
        color:red;
        border: none;
        cursor: hand;
        cursor: pointer;
        text-decoration:underline;  //加下劃線
    }
#ordersubmit:focus { 
        outline: none;    //去邊框
    } 
    
</style>
</head>
<body>
	<h1>CheckOut Confirm</h1>
	<hr>
	<table border="1" style="margin:auto;">
		<tr>
			<th width="200">Name</th><th width="100">Author</th><th width="100">Publisher</th><th width="100">Price</th>
			<th width="120">Quantity</th><th width="120"><h3>Total</h3></th>
		</tr>
	</table>
	<form action="shopping.html" method="post">
	<table border="1" style="margin:auto;">
		<c:forEach var="cart" items="${shoppingcart}" varStatus="s">
		<tr>
			<td width="200"><div align="center">${cart.name}</div><input type='hidden' name='order_bId' value='${cart.id}'><input type='hidden' name='order_bName' value='${cart.name}'/></td>
			<td width="100"><div align="center">${cart.author}</div><input type='hidden' name='order_bAuthor' value='${cart.author}'/></td>
			<td width="100"><div align="center">${cart.publisher}</div><input type='hidden' name='order_bPublisher' value='${cart.publisher}'/></td>
			<td width="100"><div align="center">${cart.price}</div><input type='hidden' name='order_price' value='${cart.price}'/></td>
			<td width="120"><div align="center">${cart.quantity}</div><input type='hidden' name='order_quantity' value='${cart.quantity}'/></td>
			<td width="120"><div align="center"></div></td>
		</tr>
		</c:forEach>
		<%  
			Double amount = (Double) request.getAttribute("amount");
		%>
		<tr><td colspan="6"><div align="right"><em><b><%=amount%></b></em></div><input type='hidden'name='order_amount' value=<%=amount %>/></td></tr>
	</table>
	<table style="margin:auto;">
		<tr>
			<td colspan="3"><div align="center"><a href="redirect.jsp"><font size="+1">Continue Shopping</font></a></div></td>
			<td colspan="3"><div align="center">
					<input type="submit" name="action" value="BUY" id="ordersubmit">
			</div></td>
		</tr>	
	</table>
    </form>	
		
		
</body>
</html>