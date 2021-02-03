package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Employee;
import utils.DBUtil;

public class EmployeeDAO {
    // データベース接続と結果取得のための変数
    private PreparedStatement pstmt;
    private ResultSet rs;  //getAllEmployees

    public List<Employee> getEmployeeFromName() {
        // メソッドの結果として返すリスト
        List<Employee> results = new ArrayList<Employee>();

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DBUtil.getConnection();

            // 3.DBとやりとりする窓口(Statementオブジェクト)の作成
            String sql = "select * from employees order by id desc";
            pstmt = con.prepareStatement(sql);

            // 4,5. Select文の実行と結果を格納／代入

            rs = pstmt.executeQuery();

            // 6. 結果を表示する
            while (rs.next()) {
                // 1件ずつCountryオブジェクトを生成して結果を詰める
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setCode(rs.getString("Code"));
                employee.setName(rs.getString("Name"));
                employee.setPassword(rs.getString("password"));
                employee.setAdmin_flag(rs.getInt("admin_flag"));
                employee.setCreated_at(rs.getTimestamp("created_at"));
                employee.setUpdated_at(rs.getTimestamp("updated_at"));
                employee.setDelete_flag(rs.getInt("delete_flag"));

                // リストに追加
                results.add(employee);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtil.close();
        }
        return results;
    }

}
