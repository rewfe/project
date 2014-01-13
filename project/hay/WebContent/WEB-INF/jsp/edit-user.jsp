<%@page pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<link rel="stylesheet" type="text/css" href="./css/extend.css" />
<title><fmt:message key="title.editUser" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/include/menu.jsp" />
<c:url var="action" value="./edit-user.html" >
	<c:param name="event" value="update" />
</c:url>
<form action="<c:out value="${action}" />" method="post">
<div class="block">
<div class="head"><b><fmt:message key="label.user" /> N${user.id}</b></div>
<div class="body"><input type="hidden" name="id" value="${user.id}" />
<table class="infoTable">
	<tr>
		<td><fmt:message key="label.id" />:</td>
		<td>${user.id}</td>
	</tr>
	<tr>
		<td><fmt:message key="label.login" />:</td>
		<td><input name="login" value="<c:out value="${user.name}" />" /></td>
	</tr>
	<tr>
		<td><fmt:message key="label.password" />:</td>
		<td><input name="password" value="<c:out value="${user.password}" />" /></td>
	</tr>
	<tr>
		<td><fmt:message key="label.role" />:</td>
		<td><select name="role">
			<c:forEach var="role" items="${roles}">
				<option value="${role.name}" <c:if test="${role == user.role}">selected="selected"</c:if>>${role.name}</option>
			</c:forEach>
		</select></td>
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