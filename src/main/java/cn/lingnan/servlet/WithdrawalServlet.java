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
@WebServlet("/withdrawal")
public class WithdrawalServlet extends HttpServlet {

    TransferService transferDepository = new TransferServiceImpl();
    private DiaryService diaryDao = new DiaryServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取转账表单信息
        String mycardId = request.getParameter("mycardId");
        Double amountTransferred = Double.valueOf(request.getParameter("amountTransferred"));
        String payee = request.getParameter("payee");
        String cardidOfPayee = request.getParameter("cardidOfPayee");
        String remarks = request.getParameter("remarks");

        boolean flag = transferDepository.transfer(mycardId,amountTransferred,payee,cardidOfPayee,remarks);
        if(flag) {
            diaryDao.insertDiary(mycardId , "取款成功");
            request.getRequestDispatcher("/WEB-INF/Pages/transfersuccess.jsp").forward(request, response);
//            System.out.println("转账成功！！！！");
        }else{
            request.setAttribute("error", "yes");
            request.getRequestDispatcher("/WEB-INF/Pages/withdrawal.jsp").forward(request, response);
        }


    }

}
