<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 <%@ page import = "com.vo.Manager"  %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<h2 align = "center"> Manger Records</h2>

	<div align = center>
	
	<table border = 2>
		
		<tr><th> customer ID</th><th>CustomerFirstName</th><th>CustomerLastName</th>
		<th>CustomerPhoneNum</th> <th>CustomerAddress</th> <th>CustomerBalance</th> </tr> 
		
		<c:forEach items = "${Resultlist}" var="ResultObj">
  			<tr>  
  				<td> ${ResultObj.getManagerID() }  </td>
				<td> ${ResultObj.getManager_FirstName()} </td>
			 	<td> ${ResultObj.getManager_LastName()} </td>
			 	<td> ${ResultObj.getManager_Phone()} </td>
			 	<td> ${ResultObj.getManager_Address()} </td>
			 	<td> ${ResultObj.getManager_EmpolyeeID()} </td>
			 </tr>
		</c:forEach>
		
		
	</table>
	
	


</body>
</html>