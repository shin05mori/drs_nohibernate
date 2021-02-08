package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Employee;
import utils.DBUtil;

public class CountDAO {
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<Employee> getEnployeesCount() {
        List<Employee> results = new ArrayList<Employee>();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "select count(*) from employees";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setCount(rs.getInt("count(*)"));
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
