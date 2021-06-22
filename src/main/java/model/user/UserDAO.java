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
            String sql = "insert into Users(name, email, password, created_at, updated_at) values(?, ?, ?, ?, ?)";

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
}