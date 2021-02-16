package controllers.reports;

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
import dao.SelectDAO;
import models.Employee;
import models.Report;

/**
 * Servlet implementation class ReportsFollowlistServlet
 */
@WebServlet("/reports/rlist")
public class ReportsFollowlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsFollowlistServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SelectDAO dao = new SelectDAO();
        Report r = dao.selectReportCode(Integer.parseInt(request.getParameter("id")));
        Employee emp = dao.selectCode(Integer.parseInt(request.getParameter("id")));

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        CountDAO dao1 = new CountDAO();
        Report count = dao1.getMyReportsCount(r.getId());
        r.setCount(count.getCount());
        long reports_count = (long)r.getCount();

        PageDAO dao2 = new PageDAO();
        int first = (15 * (page - 1));
        int last = 15;

        List<Report> reports = dao2.selectMyAllReportPage(r.getId(), first, last);

        request.setAttribute("employee", emp);
        request.setAttribute("reports", reports);
        request.setAttribute("reports_count", reports_count);
        request.setAttribute("page", page);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/rlist.jsp");
        rd.forward(request, response);
    }

}