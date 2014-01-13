<%@page pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<link rel="stylesheet" type="text/css" href="./css/extend.css" />
<title><fmt:message key="title.createNews" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/include/menu.jsp" />
<c:url var="action" value="./create-news.html" >
	<c:param name="event" value="create" />
</c:url>
<form action="<c:out value="${action}" />" method="post">
<div class="block">
<div class="head"><b><fmt:message key="header.createNews" /></b></div>
<div class="body">
<table class="infoTable">
	<tr>
		<td><fmt:message key="label.header" />:</td>
		<td><input class="total" name="headline" type="text" /></td>
	</tr>
	<tr>
		<td><fmt:message key="label.message" />:</td>
		<td><textarea class="total" name="message" rows="20" cols="10"></textarea></td>
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