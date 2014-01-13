<%@page pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<link rel="stylesheet" type="text/css" href="./css/extend.css" />
<title><fmt:message key="title.userRoom" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/include/menu.jsp" />
<form action="./create-mail.html" method="post">
<div class="block">
<div class="head"><b> 
<fmt:message key="header.sendPrivate">
	<fmt:param><c:out value="${user.name}" /></fmt:param>
</fmt:message> 
</b></div>
<div class="body">
<input type="hidden" name="recipient" value="${user.id}" />
<table class="infoTable">
	<tr>
		<td><fmt:message key="label.subject" />:</td>
		<td><input name="subject" type="text" class="total" /></td>
	</tr>
	<tr>
		<td><fmt:message key="label.message" />:</td>
		<td><textarea class="total" name="message" rows="10" cols=""></textarea></td>
	</tr>
	<tr>
		<td />
		<td class="section"><input type="submit" value="<fmt:message key="button.send" />" /></td>
	</tr>
</table>
</div>
</div>
</form>
</body>
</html>