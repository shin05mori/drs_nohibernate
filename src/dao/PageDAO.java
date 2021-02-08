package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Employee;
import models.Report;
import utils.DBUtil;

public class PageDAO {
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<Employee> selectPage(int a, int b) {
        List<Employee> results = new ArrayList<Employee>();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "select * from employees order by id desc limit ?, ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, a);
            pstmt.setInt(2, b);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setCode(rs.getString("code"));
                employee.setName(rs.getString("name"));
                employee.setPassword(rs.getString("password"));
                employee.setAdmin_flag(rs.getInt("admin_flag"));
                employee.setCreated_at(rs.getTimestamp("created_at"));
                employee.setUpdated_at(rs.getTimestamp("updated_at"));
                employee.setDelete_flag(rs.getInt("delete_flag"));
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

    public List<Report> selectReportPage(int a, int b) {
        List<Report> results = new ArrayList<Report>();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "select * from reports order by id desc limit ?, ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, a);
            pstmt.setInt(2, b);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Report report = new Report();
                report.setId(rs.getInt("id"));
                report.setEmployee_id(rs.getInt("employee_id"));
                report.setReport_date(rs.getDate("report_date"));
                report.setTitle(rs.getString("title"));
                report.setContent(rs.getString("content"));
                report.setCreated_at(rs.getTimestamp("created_at"));
                report.setUpdated_at(rs.getTimestamp("updated_at"));
                results.add(report);
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
