<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String action=request.getParameter("action");

	if("logout".equals(action)){
		session.invalidate();
		out.println("<a href='listener.jsp'>返回</a>");
		return;
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Listener Page</title>
		<style>body,td{font-size:14px; color:blue;}</style>
	</head>
	<body>
		<table border="l">
			<tr>
				<td>
					<a href="listener.jsp?action=logout">刪除Session</a>
				</td>
			</tr>
		</table>
	</body>
</html>