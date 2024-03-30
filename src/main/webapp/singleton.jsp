<%@ page import="java.util.Date"%>
<%@ page import="mad.nthu.ch3_listener.PersonInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<%
	String action=request.getParameter("action");
	String account=request.getParameter("account");
	
	if("login".equals(action) && account.trim().length()>0){
		PersonInfo personInfo=new PersonInfo();
		personInfo.setAccount(account.trim().toLowerCase());
		personInfo.setIp(request.getRemoteAddr());
		personInfo.setLoginDate(new Date());
		
		session.setAttribute("personInfo", personInfo);
		
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
		return;
	}
	else if("logout".equals(action)){
		session.removeAttribute("personInfo");
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
		return;
	}
	
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>body{font-size:12px;}</style>
	</head>
	<body>
		<c:choose>
			<c:when test="${ personInfo != null }">
				歡迎您，${ personInfo.account }。<br>
				您的登入IP為 ${ personInfo.ip }，<br>
				登入時間為<fmt:formatDate value="${ personInfo.loginDate }" pattern="yyyy-MM-dd HH:mm"/>
				
				<a href="${ pageContext.request.requestURI }?action=logout">退出</a>
				<script>setTimeout("location=location;",5000);</script>
			</c:when>
			<c:otherwise>
				${ msg }
				<c:remove var="msg" scope="session"/>
				<form action="${ pageContext.request.requestURI }?action=login" method="post">
					帳號:
					<input type="text" name="account">
					<input type="submit" value="登入">
				</form>
			</c:otherwise>
		</c:choose>
	</body>
</html>