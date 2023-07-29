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
@WebServlet("/deposit")
public class depositServlet extends HttpServlet {

    TransferService transferDepository = new TransferServiceImpl();
    private DiaryService diaryDao = new DiaryServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取转账表单信息
        String mycardId = request.getParameter("mycardId");
        Double amountTransferred = Double.valueOf(request.getParameter("amountTransferred"));

        boolean flag = transferDepository.deposit(mycardId,amountTransferred);
        if(flag) {
            diaryDao.insertDiary(mycardId , "存款成功");
            request.getRequestDispatcher("/WEB-INF/Pages/transfersuccess.jsp").forward(request, response);
//            System.out.println("转账成功！！！！");
        }else{
            request.setAttribute("error", "yes");
            request.getRequestDispatcher("/WEB-INF/Pages/deposit.jsp").forward(request, response);
        }


    }

}
