<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新規ユーザー情報登録</title>
</head>
<body>
<p>ユーザー情報を入力してください</p>
<form action="/User/SignUp" method="post">
    <table>
        <tr>
            <td>氏名</td>
            <td><input type="text" name="name" required /></td>
        </tr>
        <tr>
            <td>メールアドレス</td>
            <td><input type="email" id="mail" name="mail" required /></td>
        </tr>
        <tr>
            <td>パスワード</td>
            <td><input type="password" pattern="^[0-9A-Za-z]+$" name ="pass" required /></td>
        </tr>
    </table>
    <input type="submit" name="button" value="登録">
</form>
</body>
</html>
