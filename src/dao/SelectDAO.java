package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Employee;
import models.Report;
import utils.DBUtil;

public class SelectDAO {
    private PreparedStatement pstmt;
    private ResultSet rs;

    public Employee selectCode(Integer id) {
        Employee results = new Employee();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "select * from employees where id = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);
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

    public Report selectReportCode(Integer id) {
        Report result = new Report();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "select * from reports where id = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()){
            Employee employee = new Employee();
            result.setId(rs.getInt("id"));
            result.setEmployee_id(rs.getInt("employee_id"));
            result.setReport_date(rs.getDate("report_date"));
            result.setTitle(rs.getString("title"));
            result.setContent(rs.getString("content"));
            result.setCreated_at(rs.getTimestamp("created_at"));
            result.setUpdated_at(rs.getTimestamp("updated_at"));
            employee = selectCode(rs.getInt("employee_id"));
            result.setEmployee(employee);
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
        return result;
    }

    public Employee selectE_id(Integer id) {
        Employee results = new Employee();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "select * from follow where employee_id = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()){
            results.setFollow_id(rs.getInt("follow_id"));
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
