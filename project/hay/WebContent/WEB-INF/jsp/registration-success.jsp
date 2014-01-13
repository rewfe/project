<%@page pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<title><fmt:message key="title.registrationSuccess" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/include/menu.jsp" />
<div class="centerBlock">
<div class="head"><b><fmt:message key="header.registrationSuccess" /></b></div>
<div class="body"><fmt:message key="msg.registrationSuccess" >
<fmt:param><b><a href="./user-room.html"><c:out value="${theUser.name}" /></a></b></fmt:param>
</fmt:message></div>
</div>
</body>
</html>