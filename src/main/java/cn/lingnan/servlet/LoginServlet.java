package cn.lingnan.servlet;
import cn.lingnan.entity.Depositor;

import cn.lingnan.service.DiaryService;
import cn.lingnan.service.Impl.DiaryServiceImpl;
import cn.lingnan.service.Impl.LoginServiceImpl;
import cn.lingnan.service.Impl.ManageServiceImpl;
import cn.lingnan.service.LoginService;
import cn.lingnan.service.ManageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginRepository = new LoginServiceImpl();
    private ManageService manageDepository = new ManageServiceImpl();
    private DiaryService diaryDao = new DiaryServiceImpl();
    private Depositor depositor = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("user");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username+" "+password);

        if (user.equals("depositor")) {
            boolean isLogin = loginRepository.isLogin(username, password);
            System.out.println(isLogin);
            if (isLogin) {
                HttpSession session = request.getSession();
                depositor = loginRepository.findAllOfDepositor(username);
                session.setAttribute("depositor", depositor);
                if (!isUserAlreadyLoggedIn(session)) {
                    diaryDao.insertDiary(depositor.getCardid(), "登录系统");
                    markUserAsLoggedIn(session);
                }
                request.getRequestDispatcher("/WEB-INF/Pages/service.jsp").forward(request, response);
            } else {
                response.sendRedirect("index.jsp?error=yes");
            }
        }

        if(user.equals("admin")){

            boolean isLoginOfAdmin = loginRepository.isLoginOfAdmin(username,password);
            System.out.println(isLoginOfAdmin);
            if (isLoginOfAdmin) {
                HttpSession session = request.getSession();
                session.setAttribute("admin",username);
                List<Depositor> list = manageDepository.findAll();
                session.setAttribute("list", list);
                request.getRequestDispatcher("/WEB-INF/Pages/manage.jsp").forward(request, response);
            } else {
                response.sendRedirect("index.jsp?error=yes");

            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");

        if(method.equals("success")){

            request.getRequestDispatcher("/WEB-INF/Pages/service.jsp").forward(request,response);

        }else if(method.equals("adminsuccess")){

            request.getRequestDispatcher("/WEB-INF/Pages/manage.jsp").forward(request, response);
        }

    }
    private boolean isUserAlreadyLoggedIn(HttpSession session) {
        return session.getAttribute("loggedIn") != null;
    }

    private void markUserAsLoggedIn(HttpSession session) {
        session.setAttribute("loggedIn", true);
    }
}
