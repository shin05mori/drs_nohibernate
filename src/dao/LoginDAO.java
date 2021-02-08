package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Employee;
import utils.DBUtil;

public class LoginDAO {
    private PreparedStatement pstmt;
    private ResultSet rs;

    public Employee checkLoginCodeAndPassword(String code, String password) {
        Employee results = new Employee();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "select * from employees where delete_fag = 0 and code = ? and password = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, code);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if(rs.next()){
                results.setId(rs.getInt("id"));
                results.setCode(rs.getString("code"));
                results.setName(rs.getString("name"));
                results.setPassword(rs.getString("password"));
                results.setAdmin_flag(rs.getInt("admin_flag"));
                results.setCreated_at(rs.getTimestamp("created_at"));
                results.setUpdated_at(rs.getTimestamp("updated_at"));
                results.setDelete_flag(rs.getInt("delete_flag"));
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
