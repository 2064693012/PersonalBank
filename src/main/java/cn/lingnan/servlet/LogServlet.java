package cn.lingnan.servlet;

import cn.lingnan.service.DiaryService;
import cn.lingnan.service.Impl.DiaryServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import cn.lingnan.entity.Log;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/log")
public class LogServlet extends HttpServlet {
    private DiaryService diaryDao = new DiaryServiceImpl();
    private String cardID = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cardID = request.getParameter("cardID");
        int page = Integer.parseInt(request.getParameter("page")); // Get the page number from the request
        int pageSize = 10; // You can set the page size as per your requirement

        // Call the getAllDiaryByPage method to get logs for the specified cardID and page
        List<Log> logs = diaryDao.getAllDiaryByPage(cardID, page, pageSize);
        int logCount = diaryDao.getLogCount(cardID);

        // Set the logs attribute in the request to be used in the JSP
        request.setAttribute("logs", logs);
        request.setAttribute("logCount", logCount);
        request.setAttribute("cardID", cardID); // To pass the cardID back to the JSP for pagination links
        request.setAttribute("currentPage", page); // Set the current page number in the request
        // Forward the request to the JSP to display the logs
        request.getRequestDispatcher("/WEB-INF/Pages/Log.jsp").forward(request, response);
    }
}
