package cn.lingnan.servlet;
import cn.lingnan.service.DiaryService;
import cn.lingnan.service.Impl.DiaryServiceImpl;
import cn.lingnan.service.Impl.TransferServiceImpl;
import cn.lingnan.service.TransferService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 处理生活缴费页面请求
 *
 */

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    private DiaryService diaryDao = new DiaryServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TransferService transferDepository = new TransferServiceImpl();
        //获取转账表单信息
        String cardId = request.getParameter("cardId");
        String company = request.getParameter("company");
        String num = request.getParameter("num");
        Double money = Double.valueOf(request.getParameter("money"));
        String remarks = request.getParameter("remarks");

        boolean flag = transferDepository.transfer(cardId, money ,company," ",num);
        if(flag) {
            diaryDao.insertDiary(cardId, "成功缴了水电费");
            request.getRequestDispatcher("/WEB-INF/Pages/transfersuccess.jsp").forward(request, response);
        }else{
            request.setAttribute("error", "yes");
            diaryDao.insertDiary(cardId, "缴水电费失败，银行卡余额不足");
            request.getRequestDispatcher("/WEB-INF/Pages/payment.jsp").forward(request, response);
//            request.getRequestDispatcher("fail.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");

        switch (method){
            case "gas":
                request.getRequestDispatcher("/WEB-INF/Pages/gasPayment.jsp").forward(request, response);
                break;
            case "waterAndElectricity":
                request.getRequestDispatcher("/WEB-INF/Pages/WaterPayment.jsp").forward(request, response);
                break;

        }

    }
}
