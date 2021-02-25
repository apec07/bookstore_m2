<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="idv.cm.db.UserVO"%>
<%@ page import="idv.cm.db.OrderedListVO"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<%
UserVO user = (UserVO) session.getAttribute("user");
if (user == null) {
	response.sendRedirect(request.getContextPath() + "/login.jsp");
}
/** 
List<OrderedListVO> lista = (List<OrderedListVO>) request.getAttribute("req_Order");
if(lista==null){
	out.println("request no orderedlist");
}
List<OrderedListVO> listb = (List<OrderedListVO>) session.getAttribute("session_Order");
if(listb==null){
	out.println("session no orderedlist");
}**/
%>
<title>${user.name} Order List</title>
</head>
<body>	
	<table border="1">
	 	<tr>
			<th width="200">orderNumber</th>
			<th width="100">bookName</th>
			<th width="100">bookAuthor</th>
			<th width="100">bookPrice</th>
			<th width="120">orderQuantity</th>
			<th width="120">orderTime</th>
		</tr>
	
		<c:forEach var="list" items="${session_Order}">
		<!-- 
		combine order into one
			<c:set var="salary" scope="session" value="${2000*2}"/>
			<c:if test="${salary > 2000}">
			</c:if>
			 -->
			<tr>
				<td><div align="center"><c:out value="${list.orderNumber}" default="no books"/></div></td>
				<td><div align="center">${list.bookName}</div></td>
				<td><div align="center">${list.bookAuthor}</div></td>
			    <td><div align="center">${list.bookPrice}</div></td>
				<td><div align="center">${list.orderQuantity}</div></td>
				<td><div align="center">${list.orderTime}</div></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>