<%@page pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<link rel="stylesheet" type="text/css" href="./css/extend.css" />
<title><fmt:message key="title.userList" /></title>
<script type="text/javascript">
	function changeSelection(){
		var manipulator = document.getElementById('manipulator');
	 	var mailIds = document.getElementsByName('mailIds');
	 	for(var i=0; i < mailIds.length; i++){
	 		mailIds[i].checked = manipulator.checked;
		}
	}
</script>
</head>
<body>
<form action="./mail-list.html" method="post">
<jsp:include page="/WEB-INF/include/menu.jsp" />
<div style="margin-bottom: 5px; ">
	<fmt:message key="label.action" />: 
	<select name="event">
		<option value="delete"><fmt:message key="label.action.delete" /></option>
		<option value="read"><fmt:message key="label.action.asRead" /></option>
	</select>	
	<input type="submit" value="Применить" />
</div>
<table class="list">
<thead>
<tr>
	<td><input id="manipulator" type="checkbox" onclick="changeSelection()" /></td>
	<td><fmt:message key="label.subject" /></td>
	<td><fmt:message key="label.who" /></td>
	<td><fmt:message key="label.date" /></td>
	<td class="total"></td>
</tr>
</thead> 
<c:set var="switcher" value="even" />  
<c:forEach var="mail" items="${mails}" varStatus="switcher">
	<c:url var="mailUrl" value="./show-mail.html" >
		<c:param name="id" value="${mail.id}" />
	</c:url>
	<c:url var="userUrl" value="./user-info.html" >
		<c:param name="id" value="${mail.sender.id}" />
	</c:url>
	<c:set var="rowStyle" value="${switcher.count%2==0 ? 'even' : 'odd'}" />
	<c:set var="linkStyle" value="${not mail.delivered ? 'notread' : 'read'}" />
		<tr class="${rowStyle}">
			<td><input type="checkbox" name="mailIds" value="${mail.id}" /></td>
			<td><a class="${linkStyle}" href="<c:out value="${mailUrl}" />"> <c:out value="${mail.subject}" /> </a></td>
			<td><a href="<c:out value="${userUrl}" />"> <c:out value="${mail.sender.name}" /> </a></td>
			<td colspan="2"><c:out value="${mail.birthday}" /></td>
		</tr>
	</c:forEach>
</table>
</form>
</body>
</html>