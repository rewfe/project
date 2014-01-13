<%@page pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<link rel="stylesheet" type="text/css" href="./css/extend.css" />
<title><fmt:message key="title.loginSuccess" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/include/menu.jsp" />
<div class="centerBlock">
<div class="head"><b><fmt:message key="header.identification" /></b></div>
<div class="body" style="text-align: center;">
	<fmt:message key="msg.loginSuccess">
		<fmt:param><b><a href="./user-room.html"><c:out value="${theUser.name}" /></a></b></fmt:param>
	</fmt:message>
</div>
</div>
</body>
</html>