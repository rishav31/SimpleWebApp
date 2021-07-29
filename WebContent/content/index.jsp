<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pasting Service</title>
</head>
<body>
	<h1>Clipboard Pasting Service</h1>

	<form action="../index" method="post">

		<span>Paste Content:</span><br /> <input type="text"
			name="textContent" /> <br /> <br />Expiration:<input type="number"
			name="expiration" /><br /> <br /> Name/Title:<input type="text"
			name="title" /><br /> <br /> Password:<input type="password"
			name="password" /><br /> <br /> Exposure: <select name="exposure">
			<option>Default</option>
			<option>Private</option>
			<option>other</option>
		</select> <br /> <br /> <input type="submit" value="register" />

	</form>
	<c:if test="${not empty message}">
		<h1>${message}</h1>
		<a href="${url}">Update</a>
	</c:if>
</body>
</html>