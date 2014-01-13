<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="/WEB-INF/taglib/hay.tld" prefix="hay" %>
<div class="menu">
<hay:ifAuth path="/main.html"><a href="./main.html" class="toLeft"><fmt:message key="link.main" /></a></hay:ifAuth> 
<hay:ifAuth path="/public-room.html"><a href="./public-room.html" class="toLeft"><fmt:message key="link.chat" /></a></hay:ifAuth>
<hay:ifAuth path="/user-list.html"><a href="./user-list.html" class="toLeft"><fmt:message key="link.list" /></a></hay:ifAuth>
<hay:ifAuth path="/mail-list.html"><a href="./mail-list.html" class="toLeft"><fmt:message key="link.mail" /><c:if test="${not empty unreadMailCount}" >(${unreadMailCount})</c:if></a></hay:ifAuth>
<hay:ifAuth path="/registration.html"><a href="./registration.html" class="toRight"><fmt:message key="link.registration" /></a></hay:ifAuth>
<hay:ifAuth path="guest"><a href="./login.html" class="toRight"><fmt:message key="link.login" /></a></hay:ifAuth>
<hay:ifAuth path="/logout.html"><a href="./logout.html" class="toRight"><fmt:message key="link.logout" /></a></hay:ifAuth>
<hay:ifAuth path="/user-room.html"><a href="./user-room.html" class="toRight"><c:out value="'${theUser.name}'" /></a></hay:ifAuth>
<hay:ifAuth path="/damned-info.html"><a href="./damned-info.html" class="toRight" style="color: #b51616;"><fmt:message key="link.ban" /></a></hay:ifAuth>
</div>
<c:if test="${not empty requestScope['my.sample.Message']}">
	<div class="message" ><fmt:message key="${requestScope['my.sample.Message']}" /></div>
</c:if>
<c:if test="${not empty requestScope['my.sample.Warning']}">
	<div class="warning" ><fmt:message key="${requestScope['my.sample.Warning']}" /></div>
</c:if>
<c:if test="${not empty requestScope['my.sample.Error']}">
	<div class="error" ><fmt:message key="${requestScope['my.sample.Error']}" /></div>
</c:if>