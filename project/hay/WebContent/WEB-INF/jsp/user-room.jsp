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
<form action="?event=update" method="post">
<div class="block">
<div class="head"><b><fmt:message key="label.changePassword" /></b></div>
<div class="body">
<table class="infoTable">
	<tr>
		<td><fmt:message key="label.password" />:</td>
		<td><input name="oldPassword" type="password" /></td>
	</tr>
	<tr>
		<td><fmt:message key="label.newPassword" />:</td>
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