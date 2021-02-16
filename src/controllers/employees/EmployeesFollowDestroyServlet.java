package controllers.employees;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TransactionDAO;
import models.Employee;

/**
 * Servlet implementation class EmployeesFollowDestroyServlet
 */
@WebServlet("/follow/destroy")
public class EmployeesFollowDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesFollowDestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            //SelectDAO dao1 = new SelectDAO();
            //Report r = dao1.selectReportCode(Integer.parseInt(request.getParameter("id")));
            Employee e = (Employee)request.getSession().getAttribute("login_employee");

            e.setFollow_id(Integer.parseInt(request.getParameter("id")));

            TransactionDAO dao = new TransactionDAO();
            dao.followDestroy(e.getId(), e.getFollow_id());

            request.getSession().setAttribute("flush", "リストから外しました");

            response.sendRedirect(request.getContextPath() + "/followlist");
        }
    }
