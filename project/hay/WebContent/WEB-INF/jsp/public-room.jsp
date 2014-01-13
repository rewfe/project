<%@page pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<link rel="stylesheet" type="text/css" href="./css/extend.css" />
<title><fmt:message key="title.chat" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/include/menu.jsp" />
<div><c:forEach var="note" items="${notes}">
	<div class="block">
		<div class="head">
			<c:url var="href" value="./user-info.html"><c:param name="id" value="${note.user.id}" /></c:url>
			<a href="<c:out value="${href}" />"><c:out value="${note.user.name}" /></a>
		</div>		
		<div class="body">
			<pre><c:out value="${note.message}" /></pre>
		</div> 
	</div>
</c:forEach>
</div>
<hay:ifAuth path="user">
<c:url var="action" value="./create-note.html">
	<c:param name="event" value="create" />
</c:url>
<form action="<c:out value="${action}" />" method="post">
<div class="block">
<div class="head"><b><fmt:message key="header.addMessage" /></b></div>
<div class="body">
<table class="infoTable">
	<tr>
		<td><fmt:message key="label.message" />:</td>
		<td><textarea name="message" class="total" rows="10" cols=""></textarea></td>
	</tr>
	<tr>
		<td />
		<td class="section"><input type="submit" value="<fmt:message key="button.send" />" /></td>
	</tr>
</table>
</div>
</div>
</form>
</hay:ifAuth>
</body>
</html>