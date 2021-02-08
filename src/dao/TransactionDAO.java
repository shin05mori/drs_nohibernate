package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import models.Employee;
import utils.DBUtil;

public class TransactionDAO {
    private PreparedStatement pstmt;
    private ResultSet rs;

    public Employee setInsert(String code, String name, String password, int admin_flag, Timestamp created_at, Timestamp updated_at, int delete_flag) {
        Employee results = new Employee();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "insert into employees (code, name, password, admin_flag, created_at, updated_at, delete_flag) values (?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, code);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setInt(4, admin_flag);
            pstmt.setTimestamp(5, created_at);
            pstmt.setTimestamp(6, updated_at);
            pstmt.setInt(7, delete_flag);

            pstmt.executeUpdate();

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

    public Employee setUpdate(int id,String code, String name, String password, int admin_flag, Timestamp updated_at, int delete_flag) {
        Employee results = new Employee();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "update employees set code = ?, name = ?, password = ?, admin_flag = ?, updated_at = ?, delete_flag = ? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, code);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setInt(4, admin_flag);
            pstmt.setTimestamp(5, updated_at);
            pstmt.setInt(6, delete_flag);
            pstmt.setInt(7, id);

            pstmt.executeUpdate();

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

    public Employee setDestroy(int id, Timestamp updated_at,  int delete_flag) {
        Employee results = new Employee();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "update employees set updated_at = ?, delete_flag = ? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setTimestamp(1, updated_at);
            pstmt.setInt(2, delete_flag);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();

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
