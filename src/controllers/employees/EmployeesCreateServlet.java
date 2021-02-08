package controllers.employees;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TransactionDAO;
import models.Employee;
import models.validators.EmployeeValidator;
import utils.EncryptUtil;

/**
 * Servlet implementation class EmployeesCreateServlet
 */
@WebServlet("/employees/create")
public class EmployeesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");

        if(_token != null && _token.equals(request.getSession().getId())) {

            Employee emp = new Employee();

            emp.setCode(request.getParameter("code"));
            emp.setName(request.getParameter("name"));
            emp.setPassword(
                EncryptUtil.getPasswordEncrypt(
                    request.getParameter("password"),
                        (String)this.getServletContext().getAttribute("pepper")
                    )
                );
            emp.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            emp.setCreated_at(currentTime);
            emp.setUpdated_at(currentTime);
            emp.setDelete_flag(0);

            List<String> errors = EmployeeValidator.validate(emp, true, true);
            if(errors.size() > 0) {
                //con.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("employee", emp);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/new.jsp");
                rd.forward(request, response);
            } else {
                //インスタンス化して実行する場合
                TransactionDAO dao = new TransactionDAO();
                dao.setInsert(emp.getCode(), emp.getName(), emp.getPassword(), emp.getAdmin_flag(), emp.getCreated_at(), emp.getUpdated_at(), emp.getDelete_flag());

                //このクラスに直接記入する場合
                /*  PreparedStatement pstmt = null;
                    ResultSet rs = null;
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

                    pstmt.executeUpdate();
                    // con.commit();
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
                }*/

                request.getSession().setAttribute("flush", "登録が完了しました。");


                response.sendRedirect(request.getContextPath() + "/employees/index");
            }
        }
    }

}
