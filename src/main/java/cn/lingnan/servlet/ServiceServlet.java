package cn.lingnan.servlet;
import cn.lingnan.entity.Account;
import cn.lingnan.entity.Depositor;

import cn.lingnan.entity.FixedDeposit;
import cn.lingnan.service.DiaryService;
import cn.lingnan.service.FixedDepositService;
import cn.lingnan.service.Impl.DiaryServiceImpl;
import cn.lingnan.service.Impl.FixedDepositServiceImpl;
import cn.lingnan.service.Impl.LoginServiceImpl;
import cn.lingnan.service.Impl.ServiceServiceImpl;
import cn.lingnan.service.LoginService;
import cn.lingnan.service.ServiceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/service")
public class ServiceServlet extends HttpServlet {

    private LoginService loginRepository = new LoginServiceImpl();
    private ServiceService serviceDao = new ServiceServiceImpl();
    private Depositor depositor = null;
    private Account account = null;
    private DiaryService diaryDao = new DiaryServiceImpl();
    private FixedDepositService fixedDepositService = new FixedDepositServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        String method = request.getParameter("method");
        depositor = (Depositor) session.getAttribute("depositor");

        switch (method) {
            case "findAllOfInfo":
                diaryDao.insertDiary(depositor.getCardid(), "查看了个人信息");
                request.getRequestDispatcher("/WEB-INF/Pages/userInfo.jsp").forward(request, response);
                break;

            case "business":
                diaryDao.insertDiary(depositor.getCardid(), "进行业务办理");
                request.getRequestDispatcher("/WEB-INF/Pages/business.jsp").forward(request, response);
                break;

            case "findAccount":
                List<FixedDeposit> fixedDeposits = fixedDepositService.getAllFixedDeposits();
                System.out.println("Number of fixed deposits: " + fixedDeposits.size());
                request.setAttribute("fixedDeposits", fixedDeposits);
                account = serviceDao.findAllOfAccount(depositor);
                request.setAttribute("account",account);
                System.out.println(account);
                diaryDao.insertDiary(depositor.getCardid(), "查看了银行卡资金信息");
                request.getRequestDispatcher("/WEB-INF/Pages/account.jsp").forward(request, response);
                break;

            case "modify":
                diaryDao.insertDiary(depositor.getCardid(), "进行账户维护");
                request.getRequestDispatcher("/WEB-INF/Pages/modify.jsp").forward(request, response);
                break;

            case "aboutUs":
                diaryDao.insertDiary(depositor.getCardid(), "查看了团队信息");
                request.getRequestDispatcher("/WEB-INF/Pages/aboutUs.jsp").forward(request, response);
                break;
        }

    }
}
