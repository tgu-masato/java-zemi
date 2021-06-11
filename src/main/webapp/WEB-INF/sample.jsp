<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = "Miyadera";
    int age = 88;
%>
<html>
<head>
    <title>JSP サンプル</title>
</head>
<body>
    <p>私の名前は、<%= name %>です。</p>
    <p>年齢は、<%= age %>です。</p>
<%--    //formタグ--%>
    <form action = "/sample" method="post">
<%--        //テキストボックス--%>
        名前:<input type="text" name="name" ><br>
<%--        //ラジオボタン--%>
        性別:
        男:<input type="radio" name="gender" value="0">
        女:<input type="radio" name="gender" value="1"><br>
<%--        //送信ボタン--%>
        <input type="submit" value="送信">
    </form>

</body>
</html>
