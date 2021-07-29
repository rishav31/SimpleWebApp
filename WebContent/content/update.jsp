<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Content</title>
</head>
<body>
	<%-- <div action="../TextController" method="post">
		
		<c:if test="${not empty message}">
			<span>${message}</span>
			<span>Url: <p>${url}</P> </span>
			
		</c:if>
	</div> --%>
	<div >
		<form action="../SimpleWebApp/update" method="post">

			<span>Paste Content:</span>
			<br /> 
			<input type="text" name="textContent" value="${textContent}" /> <br /> 
			<br />Expiration:
			<input type="number" name="expiration" value="${expiration}" /><br /> 
			<br />Name/Title:
			<input type="text" name="title" value="${title}" /><br />
			<br /> Password:
			<input type="text" name="password" value="${password}" /><br /> 
			<br /> Exposure: 
			<select name="exposure" value="${exposure}">
				<option>Default</option>
				<option>Private</option>
				<option>other</option>
			</select> <br /> 
			<br /> <input type="submit" value="update" />
		</form>
	</div>

</body>
</html>