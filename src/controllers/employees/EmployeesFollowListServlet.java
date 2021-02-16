package controllers.employees;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CountDAO;
import dao.PageDAO;
import models.Employee;

/**
 * Servlet implementation class EmployeesFollowListServlet
 */
@WebServlet("/followlist")
public class EmployeesFollowListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesFollowListServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");


        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        CountDAO dao1 = new CountDAO();
        List<Employee> list = dao1.getFollowsCount(login_employee.getId());
        long follows_count = (long)list.get(0).getCount();

        PageDAO dao2 = new PageDAO();
        int first = (15 * (page - 1));
        int last = 15;

        List<Employee> followlist = dao2.selectFollowPageJoinSQL(login_employee.getId(), first, last);


        request.setAttribute("followlist", followlist);
        request.setAttribute("follows_count", follows_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/list.jsp");
        rd.forward(request, response);
        }

}
