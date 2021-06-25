<%--
  Created by IntelliJ IDEA.
  User: A191417
  Date: 2021/06/22
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ログイン画面</title>
</head>
<body>
<p>ログイン情報を入力してください</p>
<form action="/sessions/new" method="post">
    <table>
        <tr>
            <td>メールアドレス</td>
            <td><input type="email" id="mail" name="email" required /></td>
        </tr>
        <tr>
            <td>パスワード</td>
            <td><input type="password" pattern="^[0-9A-Za-z]+$" name ="password" required /></td>
        </tr>
    </table>
    <input type="submit" name="button" value="登録">
</form>
</body>
</html>
