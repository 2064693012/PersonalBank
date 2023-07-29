package cn.lingnan.servlet;

import cn.lingnan.service.DiaryService;
import cn.lingnan.service.Impl.DiaryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private DiaryService diaryDao = new DiaryServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the existing session, if any
        //获取转账表单信息
        String cardId = request.getParameter("cardId");
        diaryDao.insertDiary(cardId, "退出系统");
        if (session != null) {
            // Invalidate the session to clear all session attributes and log out the user
            session.invalidate();
        }

        // Redirect the user back to the index.jsp page (or any other desired page)
        response.sendRedirect("index.jsp");
    }
}
