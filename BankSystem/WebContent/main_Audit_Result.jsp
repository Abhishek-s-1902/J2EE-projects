<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.vo.Audit_Login_Table"%>
 <%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<body>

	<h2 align = "center"> log files</h2>

	<div align = center>
	
	<table border = 2>
		
		<tr><th> user ID</th><th>system user ID</th><th>login time</th>  </tr>
		
		<c:forEach items = "${audit_list}" var = "ResultObj">
  			<tr>  
  				<td> ${ResultObj.getUser_loggedin() }  </td>
				<td> ${ResultObj.getCurrent_user_loggedin()} </td>
				<td> ${ResultObj.getLogged_in_time()} </td>
			</tr>
		</c:forEach>
		
		
	</table>
</body>

</body>
</html>