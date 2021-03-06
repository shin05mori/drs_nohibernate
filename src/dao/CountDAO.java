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

    public int getReportsCount() {
    int results = 0;

    try {
        Connection con = DBUtil.getConnection();

        String sql = "select count(*) from reports";
        pstmt = con.prepareStatement(sql);

        rs = pstmt.executeQuery();

        if (rs.next()) {
            results = rs.getInt("count(*)");
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


    /*public List<Report> getReportsCount() {
        List<Report> results = new ArrayList<Report>();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "select count(*) from reports";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Report report = new Report();
                report.setCount(rs.getInt("count(*)"));
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
    }*/

    public Report getMyReportsCount(int employee_id) {
        Report results = new Report();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "select count(*) from reports where employee_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employee_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                results.setCount(rs.getInt("count(*)"));
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

    /*public List<Report> getMyReportsCount(int employee_id) {
        List<Report> results = new ArrayList<Report>();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "select count(*) from reports where employee_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employee_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Report report = new Report();
                report.setCount(rs.getInt("count(*)"));
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
    }*/

    public List<Employee> getFollowsCount(int employee_id) {
        List<Employee> results = new ArrayList<Employee>();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "select count(*) from follow where employee_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employee_id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Employee e = new Employee();
                e.setCount(rs.getInt("count(*)"));
                results.add(e);
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
