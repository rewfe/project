<%@page pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<title><fmt:message key="title.main" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/include/menu.jsp" />
<hay:ifAuth path="admin">
	<div style="margin-bottom: 5px;"><a href="./create-news.html"><fmt:message key="link.add" /></a></div>
</hay:ifAuth>
<div><c:forEach var="news" items="${newsList}">
	<div class="block">
	<div class="head" >
	<span class="toRight" style="word-spacing: 10px;">	
		<hay:ifAuth path="admin">
		<c:url var="hrefDelete" value="./delete-news.html"><c:param name="id" value="${news.id}" /></c:url>
		<c:url var="hrefEdit" value="./edit-news.html"><c:param name="id" value="${news.id}" /></c:url>
		<a href="<c:out value="${hrefDelete}" />"><fmt:message key="link.delete" /></a>
		<a href="<c:out value="${hrefEdit}" />"><fmt:message key="link.edit" /></a>
		</hay:ifAuth>
		<small ><c:out value="${news.birthday}" /></small> 
	</span>
	<b><c:out value="${news.headline}" /></b>
	</div>
	<div class="body pre">${news.message}</div>
	</div>
</c:forEach></div>
</body>
</html>