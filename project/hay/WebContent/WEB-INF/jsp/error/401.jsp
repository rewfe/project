<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<title><fmt:message key="title.main" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/include/menu.jsp" />
<div class="centerBlock">
<div class="head"><b>HTTP Status 401</b></div>
<div class="body">
<fmt:message key="msg.http401" />
</div>
</div>
</body>
</html>