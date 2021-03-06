package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
 // データベース接続と結果取得のための変数
    private static Connection con;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // 1. ドライバのクラスをJava上で読み込む
        Class.forName("com.mysql.jdbc.Driver");
        // 2. DBと接続する
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost/drs_nohibernate?useSSL=false&useUnicode=true&characterEncoding=utf8",
                "mori",
                "morishita"
                );

        return con;
    }

    public static void close() {
        // 3. 接続を閉じる
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
