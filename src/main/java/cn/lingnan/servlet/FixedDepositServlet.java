package cn.lingnan.servlet;

import cn.lingnan.dao.Impl.TransferDaoImpl;
import cn.lingnan.entity.FixedDeposit;
import cn.lingnan.service.DiaryService;
import cn.lingnan.service.FixedDepositService;
import cn.lingnan.service.Impl.DiaryServiceImpl;
import cn.lingnan.service.Impl.FixedDepositServiceImpl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

import cn.lingnan.service.Impl.TransferServiceImpl;
import cn.lingnan.service.TransferService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fixedDeposit")
public class FixedDepositServlet extends HttpServlet {
    private FixedDepositService fixedDepositService = new FixedDepositServiceImpl();
    TransferDaoImpl transferDao = new TransferDaoImpl();
    TransferService transferDepository = new TransferServiceImpl();
    private DiaryService diaryDao = new DiaryServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private void addFixedDeposit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String cardID = request.getParameter("cardID");
        Date startDate = parseDate(request.getParameter("startDate"));
        Date endDate = parseDate(request.getParameter("endDate"));
        double depositAmount = Double.parseDouble(request.getParameter("depositAmount"));
        String depositType = request.getParameter("depositType");
        double interestRate = Double.parseDouble(request.getParameter("interestRate"));
        double maturityAmount = Double.parseDouble(request.getParameter("maturityAmount"));

        transferDao.transaction(cardID,depositAmount,depositType);
        FixedDeposit fixedDeposit = new FixedDeposit(cardID, startDate, endDate, depositAmount, depositType, interestRate, maturityAmount);

        if (fixedDepositService.addFixedDeposit(fixedDeposit)) {
            diaryDao.insertDiary(cardID, depositType+"存款成功");
            request.getRequestDispatcher("/WEB-INF/Pages/transfersuccess.jsp").forward(request,response);
        } else {
            // Add error handling code here
        }
    }

    private void addCurrentDeposit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String cardID = request.getParameter("cardID");
        double depositAmount = Double.parseDouble(request.getParameter("depositAmount"));
        String depositType = request.getParameter("depositType");
        double interestRate = Double.parseDouble(request.getParameter("interestRate"));


        transferDao.transaction(cardID,depositAmount,depositType);
        FixedDeposit fixedDeposit = new FixedDeposit(cardID, new Date(),new Date(),depositAmount, depositType, interestRate, depositAmount);

        if (fixedDepositService.addFixedDeposit(fixedDeposit)) {
            diaryDao.insertDiary(cardID, depositType+"活期存款成功");
            request.getRequestDispatcher("/WEB-INF/Pages/transfersuccess.jsp").forward(request,response);
        } else {
            // Add error handling code here
        }
    }
    private void updateFixedDeposit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String cardID = request.getParameter("cardID");
        double depositAmount = Double.parseDouble(request.getParameter("depositAmount"));
        double interestRate = Double.parseDouble(request.getParameter("interestRate"));

        // 计算时间差（单位为天数）
        // 获取请求参数中的开始日期
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
// 获取当前日期
        LocalDate endDate = LocalDate.now();

// 计算时间差（单位为天数）
        long timeDiff = ChronoUnit.DAYS.between(startDate, endDate);
        // 计算本息+利息的值
        double maturityAmount = depositAmount + depositAmount * interestRate * timeDiff;
        transferDepository.deposit(cardID,maturityAmount);

        if (  fixedDepositService.deleteFixedDeposit(id,cardID)) {
            diaryDao.insertDiary(cardID,"定期或活期存款转出成功");
            request.getRequestDispatcher("/WEB-INF/Pages/transfersuccess.jsp").forward(request,response);
        } else {
            // Add error handling code here
        }
    }

    private void deleteFixedDeposit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String cardID = request.getParameter("cardID");
        if (fixedDepositService.deleteFixedDeposit(id,cardID)) {
            request.getRequestDispatcher("/WEB-INF/Pages/transfersuccess.jsp").forward(request,response);
        } else {
            // Add error handling code here
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FixedDeposit> fixedDeposits = fixedDepositService.getAllFixedDeposits();
        request.setAttribute("fixedDeposits", fixedDeposits);
        System.out.println("Number of fixed deposits: " + fixedDeposits.size());
        request.setAttribute("fixedDeposits", fixedDeposits);
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addFixedDeposit(request, response);
                    break;
                case "addCurrentDeposit":
                    addCurrentDeposit(request, response);
                    break;
                case "update":
                    updateFixedDeposit(request, response);
                    break;
                case "delete":
                    deleteFixedDeposit(request, response);
                    break;
                case "transfer":
                    // 转账操作
                    transferFixedDeposit(request, response);
                    break;
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/Pages/fixed_deposit_list.jsp").forward(request, response);
        }

    }

    private void transferFixedDeposit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String transferType = request.getParameter("transferType");
        String cardID = request.getParameter("cardID");
        double depositAmount = Double.parseDouble(request.getParameter("depositAmount"));
        int timeDiff = Integer.parseInt(request.getParameter("timeDiff2"));

        double interestRate = 0.005;
        // 假设定期利率为1.75%，计算利息
        double interest = depositAmount * interestRate;
        // 根据用户选择的转账类型进行相应的转账操作
        if (transferType.equals("toCurrent")) {
            // 将定期转回活期
            // 从定期存款表中删除对应的存款记录
            boolean success = fixedDepositService.deleteFixedDeposit(id,cardID);
            if (success) {
                // 将本息+利息存入活期存款表
                double maturityAmount = depositAmount + interest;
                depositAmount=depositAmount+depositAmount*timeDiff*interestRate/ 100;
                FixedDeposit currentDeposit = new FixedDeposit(cardID, new Date(), new Date(), maturityAmount, "活期", 0.005, depositAmount);
                boolean addSuccess = fixedDepositService.addFixedDeposit(currentDeposit);
                if (addSuccess) {
                    request.getRequestDispatcher("/WEB-INF/Pages/transfersuccess.jsp").forward(request,response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/Pages/fail.jsp").forward(request,response);
                }
            } else {
                request.getRequestDispatcher("/WEB-INF/Pages/fail.jsp").forward(request,response);
            }
        } else if (transferType.equals("toFixed")) {
            // 将活期转为定期
            // 从活期存款表中删除对应的存款记录
            boolean success =fixedDepositService.deleteFixedDeposit(id,cardID);
            if (success) {
                interestRate = 0.005;
                interest = depositAmount * interestRate;
                if (timeDiff == 1) {
                    interestRate = 1.75;
                } else if (timeDiff == 2) {
                    interestRate = 2.25;
                } else if (timeDiff == 3 || timeDiff == 5) {
                    interestRate = 2.75;
                }
// Create a Calendar instance
                Calendar calendar = Calendar.getInstance();

// Set the current date to the Calendar instance
                calendar.setTime(new Date());

// Add one year to the Calendar instance
                calendar.add(Calendar.YEAR, timeDiff);

// Get the date one year ahead
                Date nextYearDate = calendar.getTime();

                // 将本息+利息存入定期存款表
                double maturityAmount = depositAmount + interest;
                depositAmount=depositAmount+depositAmount*timeDiff*interestRate/ 100;
                FixedDeposit fixedDeposit = new FixedDeposit(cardID, new Date(), nextYearDate, maturityAmount, "定期", interestRate, depositAmount);
                boolean addSuccess = fixedDepositService.addFixedDeposit(fixedDeposit);
                if (addSuccess) {
                    request.getRequestDispatcher("/WEB-INF/Pages/transfersuccess.jsp").forward(request,response);
                } else {
                    // 转账失败处理
                    request.getRequestDispatcher("/WEB-INF/Pages/fail.jsp").forward(request,response);
                }
            } else {
                // 转账失败处理
                request.getRequestDispatcher("/WEB-INF/Pages/fail.jsp").forward(request,response);
            }
        } else {
            // 转账类型错误处理
            request.getRequestDispatcher("/WEB-INF/Pages/fail.jsp").forward(request,response);
        }
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
