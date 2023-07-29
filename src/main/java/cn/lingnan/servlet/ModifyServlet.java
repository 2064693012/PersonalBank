package cn.lingnan.servlet;
import cn.lingnan.entity.Depositor;
import cn.lingnan.service.DiaryService;
import cn.lingnan.service.Impl.DiaryServiceImpl;
import cn.lingnan.service.Impl.LoginServiceImpl;
import cn.lingnan.service.Impl.ModifyServiceImpl;
import cn.lingnan.service.LoginService;
import cn.lingnan.service.ModifyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/modify")
public class ModifyServlet extends HttpServlet {

    private ModifyService modifyRepository = new ModifyServiceImpl();
    private LoginService loginRepository = new LoginServiceImpl();
    private DiaryService diaryDao = new DiaryServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String modify = request.getParameter("modify");
        Depositor depositor;

        switch (modify) {
            case "modifyInfo":
                String tel = request.getParameter("tel");
                String gender = request.getParameter("gender");
                String address = request.getParameter("address");

                modifyRepository.modifyInfo(tel, gender, address);
                depositor = loginRepository.findAllOfDepositor(tel);
                HttpSession session = request.getSession();
                session.setAttribute("depositor", depositor);
                diaryDao.insertDiary(depositor.getCardid(), "修改了个人信息");
                request.getRequestDispatcher("/WEB-INF/Pages/userInfo.jsp").forward(request, response);
                break;
            case "modifyPassword":

                session = request.getSession();
                String method = request.getParameter("method");
                depositor = (Depositor) session.getAttribute("depositor");
                String oldPassword = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");
                String confirmPassword = request.getParameter("confirmPassword");
                modifyRepository.modifyPassword(depositor,oldPassword,newPassword,confirmPassword);
                diaryDao.insertDiary(depositor.getCardid(), "修改了密码");
                request.getRequestDispatcher("/WEB-INF/Pages/modifySuccess.jsp").forward(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");

        switch(method){

            case "modifyInfo":

                request.getRequestDispatcher("/WEB-INF/Pages/modifyInfo.jsp").forward(request,response);
                break;

            case "modifyPassword":

                request.getRequestDispatcher("/WEB-INF/Pages/modifyPassword.jsp").forward(request,response);
                break;

            case "resetPassword":

                System.out.println("没空写代码，放弃！！！！ +_+");
                break;

            case "deleteByCardId":

                Timestamp date = Timestamp.valueOf(request.getParameter("date"));
                System.out.println(date);
                modifyRepository.deleteByCardId(date);
                response.sendRedirect("/business?method=transferLog");
                break;
        }

    }
}
