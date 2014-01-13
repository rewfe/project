<%@page pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<link rel="stylesheet" type="text/css" href="./css/extend.css" />
<title><fmt:message key="title.registration" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/include/menu.jsp" />
<c:url var="action" value="./registration.html">
	<c:param name="event" value="registration" />
</c:url>
<form action="${action}" method="post">
<div class="centerBlock">
<div class="head"><b><fmt:message key="header.registration" /></b></div>
<div class="body">
<table class="infoTable">
	<tr>
		<td><fmt:message key="label.login" />:</td>
		<td><input name="login" /></td>
	</tr>
	<tr>
		<td><fmt:message key="label.password" />:</td>
		<td><input name="password" type="password" /></td>
	</tr>
	<tr>
		<td><fmt:message key="label.rePassword" />:</td>
		<td><input name="rePassword" type="password" /></td>
	</tr>
	<tr>
		<td />
		<td class="section"><input type="submit" value="<fmt:message key="button.submit" />" /></td>
	</tr>
</table>

</div>
</div>
</form>
</body>
</html>