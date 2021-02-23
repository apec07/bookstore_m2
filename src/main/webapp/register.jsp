<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Register New User</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<style>
#re_pass.validateTips{border: 1px solid transparent; padding: 0.3em;}
</style>
<body>

	<!--<button id="create-user">New User(out)</button> -->
	 
	
	<form action="UserServlet" name="newUser" method="post">
	<table border="1" style="margin:auto;">
		<tr><td colspan=3><div align='center'><b>Register to Morgan Books</b></div></td></tr>
		<tr>
			<th>User Name</th><td colspan='2'><div><input type="text" name="new_name" id="new_name"/></div></td>
		</tr>
		<tr>
			<th>Password</th><td colspan='2'><div><input type="password" name="new_password" id="new_password"/></div></td>
		</tr>
		<tr>
			<th>Re-Password</th><td colspan='2'><div><input type="password" name="re_pass" id="re_pass"/></div></td>
		</tr>
		<tr>
			<th>Email</th><td colspan='2'><div><input type="email" name="new_email" id="new_email"/></div></td>
		</tr>
		<tr>
		<td><div align='center'><input type="button"  value="Reset" id="btnReset"/></div></td>
		<td><div align='center'><input type="submit"  value="Submit" id="btnSubmit" name="submit" onclick="checkPassword()"/></div></td>
		</tr>
	</table>
	
</form>
<!-- 
	<form action="UserServlet" method="post" name="newUser" id="new-submit">
		 <div id="dialog-form" title="创建新用户">
			<fieldset>
				<label for="name">名字</label> 
				<input type="text" name="new_name" id="new_name" value="" 
				class="text ui-widget-content ui-corner-all">
				<label for="email">邮箱</label> 
				<input type="text" name="new_email"	id="new_email" value=""
				class="text ui-widget-content ui-corner-all"> 
				<label for="password">密码</label> 
				<input type="password" name="new_password" id="new_password" value=""
				class="text ui-widget-content ui-corner-all">
			</fieldset>
			 <input type="button" value="submit" onclick='myclick()'>
		 </div>
	</form>
	-->
	

</body>
<script>

    var name = $( "#new_name" );
    var email = $( "#new_email" );
    var password = $( "#new_password" );
    var repass = $( "re_pass" );
    var tips = $( ".validateTips" );
    
    function updateTips( t ) {
        tips
          .text( t )
          .addClass( "ui-state-highlight" );
        setTimeout(function() {
          tips.removeClass( "ui-state-highlight", 1500 );
        }, 500 );
      }
    
    function checkPassword(password,repass) {
        if ( password.val() !== repass.val() ) {
          updateTips(password +"is mismatch with"+ repass);
          return false;
        } else {
          return true;
        }
      }
    

	$("#create-user").button().click(function() {
		$("#dialog-form").dialog("open");
	});
	
	$("#dialog-form").dialog({
		autoOpen : false,
		height : 300,
		width : 350,
		modal : true,
		buttons : {
			"创建一个帐户" : function crateInDG() {
				//$("#new-submit").submit();
				//myclick();
				 $.post("UserServlet",
				  {
			  		new_name: name,
			  		new_email: email,
				    new_password : password
				  },
				  function(data, status){
				    alert("Data: " + data + "\nStatus: " + status);
				  });
				$(this).dialog("close");

			},
			Cancel : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
			allFields.val("").removeClass("ui-state-error");
		}
	});
	
	function myclick() {
		 
	
	}
	
</script>

</html>