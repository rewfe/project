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
<div class="block">
<div class="head"><b><fmt:message key="header.privateMessage" /></b></div>
<div class="body">
<table class="infoTable">
	<tr>
		<td><fmt:message key="label.subject" />:</td>
		<td class="border">${mail.subject}</td>
	</tr>
	<tr>
		<td><fmt:message key="label.who" />:</td>
		<td class="border"><a href="./user-info.html?id=${mail.sender.id}"><c:out value="${mail.sender.name}" /></a></td>
	</tr>
	<tr>
		<td><fmt:message key="label.message" />:</td>		
		<td class="pre border"><c:out value="${mail.message}" /></td>
	</tr>
</table>
</div>
</div>
</body>
</html>