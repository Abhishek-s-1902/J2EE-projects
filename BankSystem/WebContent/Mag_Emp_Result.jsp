<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="com.vo.customer"%>
 <%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h2 align = "center"> Employee Records</h2>

	<div align = center>
	
	<table border = 2>
		
		<tr><th> person ID</th><th>person phone</th></tr>
		
		<c:forEach items = "${Resultlist}" var = "ResultObj">
  			<tr>  
  				<td> ${ResultObj.getEmpolyeeID() }  </td>
				<td> ${ResultObj.getEmployeePhoneNum()} </td>
			 
			 </tr>
		</c:forEach>
		
		
	</table>
	
</body>
</html>