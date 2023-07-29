package cn.lingnan.servlet;

import cn.lingnan.entity.Depositor;
import cn.lingnan.entity.Transfer;
import cn.lingnan.service.DiaryService;
import cn.lingnan.service.Impl.DiaryServiceImpl;
import cn.lingnan.service.Impl.TransferServiceImpl;
import cn.lingnan.service.TransferService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/business")
public class BusinessServlet extends HttpServlet {

    private TransferService transferDepository = new TransferServiceImpl();
    private Depositor depositor = null;
    private DiaryService diaryDao = new DiaryServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String method = request.getParameter("method");
        depositor = (Depositor) session.getAttribute("depositor");

        switch(method){

            case "transfer":
                diaryDao.insertDiary(depositor.getCardid(), "进行了转账");
                request.getRequestDispatcher("/WEB-INF/Pages/transfer.jsp").forward(request,response);
                break;

            case "withdrawal":
                diaryDao.insertDiary(depositor.getCardid(), "进行了取款");
                request.getRequestDispatcher("/WEB-INF/Pages/withdrawal.jsp").forward(request,response);
                break;

            case "deposit":
                diaryDao.insertDiary(depositor.getCardid(), "进行了存款");
                request.getRequestDispatcher("/WEB-INF/Pages/deposit.jsp").forward(request,response);
                break;

            case "fixedDeposit":
                diaryDao.insertDiary(depositor.getCardid(), "进行了定期存款");
                request.getRequestDispatcher("/WEB-INF/Pages/FixedDeposit.jsp").forward(request,response);
                break;
            case "currentDeposit":
                diaryDao.insertDiary(depositor.getCardid(), "进行了活期存款");
                request.getRequestDispatcher("/WEB-INF/Pages/currentDeposit.jsp").forward(request,response);
                break;
            case "transferLog":
                List<Transfer> list = transferDepository.findAllOfTransfer(depositor);
                System.out.println(list);
                request.setAttribute("list",list);
                diaryDao.insertDiary(depositor.getCardid(), "查看了交易记录");
                request.getRequestDispatcher("/WEB-INF/Pages/transferLog.jsp").forward(request,response);
                break;
            case "payment":
                diaryDao.insertDiary(depositor.getCardid(), "进行了生活缴费");
                request.getRequestDispatcher("/WEB-INF/Pages/payment.jsp").forward(request,response);
                break;
        }

    }
}
