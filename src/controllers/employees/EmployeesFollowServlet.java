package controllers.employees;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SelectDAO;
import dao.TransactionDAO;
import models.Employee;
import models.Report;
import models.validators.EmployeeValidator;

/**
 * Servlet implementation class EmployeesFollowServlet
 */
@WebServlet("/follow")
public class EmployeesFollowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesFollowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {

            Report r = new Report();
            SelectDAO dao1 = new SelectDAO();
            r = dao1.selectReportCode((Integer)request.getSession().getAttribute("report_id"));
            Employee e = (Employee)request.getSession().getAttribute("login_employee");

            e.setFollow_id((Integer)r.getEmployee_id());

            List<String> errors = EmployeeValidator.validateF(e);
            if(errors.size() > 0) {

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("employee", e);
                request.setAttribute("errors", errors);

                Employee followed = new Employee();
                SelectDAO dao2 = new SelectDAO();
                followed = dao2.selectCode(e.getFollow_id());

                request.setAttribute("report", r);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("followed", followed);
                request.getSession().setAttribute("report_id", r.getId());
                request.getSession().setAttribute("employee_id", r.getEmployee_id());

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
                rd.forward(request, response);
            } else {

            TransactionDAO dao = new TransactionDAO();
            dao.setFollow(e.getId(), r.getEmployee_id());

            request.getSession().setAttribute("flush", r.getEmployee().getName() + "さんをフォローしました。");

            response.sendRedirect(request.getContextPath() + "/followlist");
            }
        }
    }
}
