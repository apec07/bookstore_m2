<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="idv.cm.db.UserVO"%>
<%@ page import="idv.cm.db.BookVO"%>
<%
	UserVO user = (UserVO) session.getAttribute("user");
if (user == null) {
	response.sendRedirect(request.getContextPath() + "/login.jsp");
}


//Hashtable<Integer,BookVO> books = (Hashtable<Integer,BookVO>) request.getAttribute("storeBooks");
//if(books==null){
//	response.sendRedirect(request.getContextPath()+"/booklist");
//}
%>
<html>
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<meta charset="utf-8">
<title>Morgan's Book</title>
</head>
<body>
 	<script>
 	function mychange(){
 		alert('mychanged' + $('#input_quantity').val());
 		$( "#order_quantity" ).value = $('#input_quantity').val();
 		//alert($( "#order_quantity" ).val());
 	}
 	window.onload = function loadList()
 	{
 		//alert("loadBookList");
 		//  $.get('${pageContext.request.contextPath}/booklist', function(data) {
 		//	 document.getElementById("data").firstChild.nodeValue = data;
 		//   $('#data').text(data);
 		//    });
		//$("#listA").click();
 	}
 		
             //在網頁加載後，對id=doAjaxBtn的Button設定click的function
 			/*
            $(document).ready(function(){
             
                   $.ajax({
                        type:"GET",                    //指定http參數傳輸格式為POST
                        url: "booklist",        //請求目標的url，可在url內加上GET參數，如 www.xxxx.com?xx=yy&xxx=yyy
                        data: $("#ajaxForm").serialize(), //要傳給目標的data為id=formId的Form其序列化(serialize)為的值，之
                                                        //內含有name的物件value
                        dataType: "text",               //目標url處理完後回傳的值之type，此列為一個JSON Object
                        //Ajax成功後執行的function，response為回傳的值
                        //此範列回傳的JSON Object的內容格式如右所示: {userName:XXX,uswerInterest:[y1,y2,y3,...]}
                        success : function(response){
                        	alert("success");
                        	
                        },
                        //Ajax失敗後要執行的function，此例為印出錯誤訊息
                        error:function(xhr, ajaxOptions, thrownError){
                            alert(xhr.status+"\n"+thrownError);
                        }
                    });
         
            });  
 			*/
        </script>
	
	<h1>Welcome ${user.name}</h1>
	<div align="left"><a href="logout">Logout</a></div>
	<div><a href="orderedlist#list">Ordered List</a></div>
	
	<h2>Book list</h2>
	<table border="1">
		<tr>
			<th width="200">Name</th>
			<th width="100">Author</th>
			<th width="100">Publisher</th>
			<th width="100">Price</th>
			<th width="120">Quantity</th>
			<th width="120"><img src="images/shopping-cart.png" width="45px"
				height="35px"></th>
		</tr>
	</table>
	<%--
	<div id="data">no data jquery</div>
	<HR>
	<form action="booklist" method="get" id="ajaxForm">
	<input type="submit" name="refresh" value="loadBookList" id="listA"/>
	
	Hashtable<String,BookVO> books = (Hashtable<String,BookVO>)request.getAttribute("storeBooks");
	
	</form>
	
	--%>
	<table border='1' id="ajaxForm">
	<%
	Hashtable<String,BookVO> books = (Hashtable<String,BookVO>)request.getAttribute("storeBooks");
		if(books==null){return;}
		Set<String> keys = books.keySet();
		String[] k = keys.toArray(new String[keys.size()]);
		for(String myKey : k){
			BookVO book = books.get(myKey);
			
	%>
			<tr>
						<td width="200"><div align="center"><c:out value="<%=book.getName() %>" default="no books"/></div></td>
						<td width="100"><div align="center"><c:out value="<%=book.getAuthor() %>" default="no books"/></div></td>
						<td width="100"><div align="center"><c:out value="<%=book.getPublisher() %>" default="no books"/></div></td>
						<td width="100"><div align="center"><%=book.getPrice() %></div></td>
						<td width="100"><div align="center"><input type='number' id='input_quantity<%=myKey %>' name='order_quantity' min=1 value=1 onchange='mychange()'></div></td>
						<td width="120"><div align="center">    
						<form action="shopping.html" method="post"> 
							<input type="submit" class="button" value="Add Cart">
							<input type="hidden" name="id" value="<%=book.getId()%>">
							<input type="hidden" name="name" value="<%=book.getName() %>">
							<input type="hidden" name="author" value="<%=book.getAuthor() %>"> 
							<input type="hidden" name="publisher" value="<%=book.getPublisher() %>"> 
							<input type="hidden" name="price" value="<%=book.getPrice() %>">
							<input type="hidden" name="order_quantity" id="order_quantity<%=myKey %>" value=<%=book.getQuantity() %> >  
							<input type="hidden" name="action" value="ADD">
						</form>
						</div></td>
			</tr>
	
	<%		
		}
	%>
	
	
	</table>
	<%-- 
	<HR>
	
	<table>
	
			<c:if test="${!empty storeBooks}">
				<c:forEach var="bookKey" items="${storeBooks}">
					${bookKey.key}<br> 
					<c:if test="${!empty bookKey.value}">
						<c:forEach var="book" items="${bookKey.value}">
					<tr>
						<td width="200"><div align="center"><c:out value="${book.name}" default="no books"/></div></td>
						<td width="100"><div align="center"><c:out value="${book.author}" default="no books"/></div></td>
						<td width="100"><div align="center"><c:out value="${book.publisher}" default="no books"/></div></td>
						<td width="100"><div align="center"><c:out value="${book.price}" default="no books"/></div></td>
						<td width="120"><div align="center">${book.quantity}</div></td>
						<td width="120"><div align="center">     <input type="submit" class="button" value="Add Cart"> </div></td>
					</tr>
						</c:forEach>
					</c:if>
				</c:forEach>
			</c:if> 
	
	</table>
--%>
	<jsp:include page="/cart.jsp" flush="true" />
</body>
</html>