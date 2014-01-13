<%@page pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<link rel="stylesheet" type="text/css" href="./css/extend.css" />
<title><fmt:message key="title.userList" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/include/menu.jsp" />
<table class="list">
	<thead>
		<tr>
			<hay:ifAuth path="admin">
				<td><fmt:message key="label.id" /></td>
			</hay:ifAuth>
			<td><fmt:message key="label.name" /></td>
			<hay:ifAuth path="admin">
				<td><fmt:message key="label.role" /></td>
			</hay:ifAuth>
			<hay:ifAuth path="/edit-user.html">
				<td />
			</hay:ifAuth>
			<td class="total" />
		</tr>
	</thead>
	<c:set var="switcher" value="even" />
	<c:forEach var="user" items="${users}" varStatus="switcher">
		<tr class="${switcher.count%2==0 ? 'even' : 'odd'}">
			<hay:ifAuth path="admin">
				<td>${user.id}</td>
			</hay:ifAuth>
			<c:url var="href" value="./user-info.html" ><c:param name="id" value="${user.id}" /></c:url>
			<td><a href="<c:out value="${href}" />"><c:out value="${user.name}" /></a></td>
			<hay:ifAuth path="admin">
				<td><c:out value="${user.role.name}" /></td>
			</hay:ifAuth>
			<hay:ifAuth path="/edit-user.html">
				<c:url var="href" value="./edit-user.html" ><c:param name="id" value="${user.id}" /></c:url>
				<td><a href="<c:out value="${href}" />"><fmt:message key="link.edit" /></a></td>
			</hay:ifAuth>
			<td />
		</tr>
	</c:forEach>
</table>
</body>
</html>