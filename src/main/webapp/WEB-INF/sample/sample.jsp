<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: csuser
  Date: 2019-06-19
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%
    // Servletのデータ受け取り
    request.setCharacterEncoding("UTF-8");
    String human = (String) request.getAttribute("human");
%>

<html>
<head>
    <title>RESULT</title>
</head>
<body>
<h1>出力結果</h1>
<%=human%>
</body>
</html>