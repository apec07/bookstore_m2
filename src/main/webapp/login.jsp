<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login for Morgan's bookstore</title>
<%-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

</head>

<body>

<form action="login.html" name="check" method="post">
	<table border="1" style="margin:auto;">
		<tr><td colspan=3><div align='center'><b>Welcome to Morgan Books</b></div></td></tr>
		<tr>
			<th>User Name</th><td colspan='2'><div><input type="text" name="name" id="name"/></div></td>
		</tr>
		<tr>
			<th>Password</th><td colspan='2'><div><input type="password" name="pass" id="pass"/></div></td>
		</tr>
		<tr>
		<td><div align='center'><input type="button"  value="Reset" id="btnReset"/></div></td>
		<td><div align='center'><input type="submit"  value="Submit" id="btnSubmit" name="submit"/></div></td>
		<td><div align='center'><a href="register.jsp">New User</a></div></td>
		</tr>
	</table>
	
</form>
</body>
<script>
	const name = document.querySelector('#name');
	const pass = document.querySelector('#pass');
	const btnSet = document.querySelector('#btnReset');
	const btnIn = document.querySelector('#btnSubmit');
	btnSet.addEventListener('click',clear);
	function clear(){
		name.value="";
		pass.value="";
	}
	btnIn.addEventListener('click',checkFormat);
	function checkFormat(){
		console.log(name.value.length);
		// check length then stop submit by form
	}
	
	
</script>
</html>