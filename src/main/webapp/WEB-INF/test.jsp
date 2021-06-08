<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = "中村 真人";
    String university = "東京学芸大学";
    String classes = "A類情報教育選修";
%>
<html>
<head>
    <title>JSP Test</title>
</head>
<body>
    <p>私の名前は、<%= name %>です。</p>
    <p>通っている大学は、<%= university %>です。</p>
    <p>所属学科は、<%= classes %>です。</p>
</body>
</html>
