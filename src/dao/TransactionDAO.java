package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import models.Employee;
import models.Report;
import utils.DBUtil;

public class TransactionDAO {
    private PreparedStatement pstmt;
    private ResultSet rs;

    public int setInsert(Employee emp) {
        int results = 0;

        try {
            Connection con = DBUtil.getConnection();

            String sql = "insert into employees (code, name, password, admin_flag, created_at, updated_at, delete_flag) values (?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, emp.getCode());
            pstmt.setString(2, emp.getName());
            pstmt.setString(3, emp.getPassword());
            pstmt.setInt(4, emp.getAdmin_flag());
            pstmt.setTimestamp(5, emp.getCreated_at());
            pstmt.setTimestamp(6, emp.getUpdated_at());
            pstmt.setInt(7, emp.getDelete_flag());

            results = pstmt.executeUpdate();

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

    public Employee setUpdate(Employee emp) {
        Employee results = new Employee();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "update employees set code = ?, name = ?, password = ?, admin_flag = ?, updated_at = ?, delete_flag = ? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, emp.getCode());
            pstmt.setString(2, emp.getName());
            pstmt.setString(3, emp.getPassword());
            pstmt.setInt(4, emp.getAdmin_flag());
            pstmt.setTimestamp(5, emp.getUpdated_at());
            pstmt.setInt(6, emp.getDelete_flag());
            pstmt.setInt(7, emp.getId());

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

    public Report setReportInsert(Report r) {
        Report results = new Report();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "insert into reports (employee_id, report_date, title, content, created_at, updated_at) values (?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, r.getEmployee_id());
            pstmt.setDate(2, r.getReport_date());
            pstmt.setString(3, r.getTitle());
            pstmt.setString(4, r.getContent());
            pstmt.setTimestamp(5, r.getCreated_at());
            pstmt.setTimestamp(6, r.getUpdated_at());

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

    public Report setReportUpdate(Report r) {
        Report results = new Report();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "update reports set report_date = ?, title = ?, content = ?, updated_at = ? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setDate(1, r.getReport_date());
            pstmt.setString(2, r.getTitle());
            pstmt.setString(3, r.getContent());
            pstmt.setTimestamp(4, r.getUpdated_at());
            pstmt.setInt(5, r.getId());

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

    public Employee setFollow(int employee_id, int follow_id) {
        Employee results = new Employee();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "insert into follow (employee_id, follow_id) values (?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employee_id);
            pstmt.setInt(2, follow_id);

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

    public Employee followDestroy(int employee_id, int follow_id) {
        Employee results = new Employee();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "delete from follow where employee_id = ? and follow_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employee_id);
            pstmt.setInt(2, follow_id);

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
