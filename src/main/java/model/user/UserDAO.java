package model.user;
//  自分が格納されているフォルダの外にある必要なクラス
import lib.mysql.Client;
import model.user.User;

import java.sql.*;

public class UserDAO extends Client {
    //User登録メソッド
    public static void registUser(User user) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //SQLコマンド
            String sql = "insert into users(name, email, password, created_at, updated_at) values(?, ?, ?, ?, ?)";

            connection = create();

            //SQLコマンドの実行
            stmt = connection.prepareStatement(sql);

            //現在時刻の取得
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            //SQLコマンドの?に値を代入する
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setTimestamp(4, currentTime);
            stmt.setTimestamp(5, currentTime);

            stmt.executeUpdate();
            return;

        } catch (SQLException e) {
            e.printStackTrace();
            return;

        } finally {
            close(connection, stmt, rs);
        }
    }

    public static User selectUserByEmail(String email) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from users where email = ?";

            connection = create();

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);

            rs = stmt.executeQuery();
            //スコープの問題があるので一旦外で定義
            User user = null;
            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(connection, stmt, rs);
        }
    }
}