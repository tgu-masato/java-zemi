package model.store;

import lib.mysql.Client;

import java.sql.*;

public class StoreDAO extends Client {
    // Store登録メソッド
    public static void registStore(Store store) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // SQLコマンド
            String sql = "insert into stores(name, created_at, updated_at) values(?, ?, ?)";

            connection = create();

            // SQLコマンドの実行
            stmt = connection.prepareStatement(sql);

            // 現在時刻の取得
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            //SQLコマンドの?に値を代入する
            stmt.setString(1, store.getName());
            stmt.setTimestamp(2, currentTime);
            stmt.setTimestamp(3, currentTime);

            stmt.executeUpdate();
            return;

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } finally {
            close(connection, stmt, rs);
        }
    }

    public static Store selectStoreByName (String name) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "select * from stores where name = ?";

            connection = create();

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);

            rs = stmt.executeQuery();

            // スコープの問題があるので一旦外で定義
            Store store = null;
            if (rs.next()) {
                store = new Store(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
            return store;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(connection, stmt, rs);
        }
    }
}