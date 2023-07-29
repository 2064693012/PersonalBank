package cn.lingnan.servlet;



import cn.lingnan.entity.Account;
import cn.lingnan.entity.Depositor;
import cn.lingnan.service.Impl.ManageServiceImpl;
import cn.lingnan.service.Impl.ServiceServiceImpl;
import cn.lingnan.service.ManageService;
import cn.lingnan.service.ServiceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage")
public class ManageServlet extends HttpServlet {

    private ManageService manageDepository = new ManageServiceImpl();
    private ServiceService serviceRepository = new ServiceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");
        switch (method) {
            case "add":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                String tel = request.getParameter("tel");
                String cardid = request.getParameter("cardId");
                String pid = request.getParameter("pid");
                String gender = request.getParameter("gender");
//                System.out.println(username + password + name + tel + cardid + pid + gender);
                manageDepository.add(username, password, name, tel, cardid, pid, gender);
                request.getRequestDispatcher("registersSucc.jsp").forward(request, response);
                break;
            case "update":
                name = request.getParameter("name");
                tel = request.getParameter("tel");
                cardid = request.getParameter("cardId");
                pid = request.getParameter("pid");
                gender = request.getParameter("gender");
                String address = request.getParameter("address");
                manageDepository.update(cardid, name, tel, pid, gender,address);
                List<Depositor> list = manageDepository.findAll();
                request.setAttribute("list", list);
                request.getRequestDispatcher("/WEB-INF/Pages/manage.jsp").forward(request, response);
                break;
            case "search":
                Depositor depositor = null;
                Account account = null;
                String info = request.getParameter("info");
                System.out.println(info);
                depositor = manageDepository.findById(info);
                request.setAttribute("depositor",depositor);
                account = serviceRepository.findAllOfAccount(info);
                request.setAttribute("account",account);
                request.getRequestDispatcher("/WEB-INF/Pages/seeUser.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("registersSucc.jsp").forward(request, response);
                break;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String method = request.getParameter("method");
        String cardId = request.getParameter("cardId");

        switch (method) {
            case "deleteById":
                System.out.println("删除"+cardId);
                manageDepository.deleteById(cardId);
                List<Depositor> list = manageDepository.findAll();
                request.setAttribute("list", list);
                request.getRequestDispatcher("/WEB-INF/Pages/manage.jsp").forward(request, response);
                break;
            case "findById":
                request.setAttribute("depositor", manageDepository.findById(cardId));
                request.getRequestDispatcher("/WEB-INF/Pages/manageModifyInfo.jsp").forward(request, response);
                break;
            case "seeAccount":
                Account account = null;
                Depositor depositor = null;
                depositor = manageDepository.findById(cardId);
                request.setAttribute("depositor",depositor);
                account = serviceRepository.findAllOfAccount(cardId);
                request.setAttribute("account",account);
                System.out.println(account);
                request.getRequestDispatcher("/WEB-INF/Pages/seeAccount.jsp").forward(request, response);
                break;
            case "search":
                String info = request.getParameter("info");
                System.out.println(info);
                depositor = manageDepository.findById(info);
                request.setAttribute("depositor",depositor);
                System.out.println(depositor);
                account = serviceRepository.findAllOfAccount(info);
                request.setAttribute("account",account);
                request.getRequestDispatcher("/WEB-INF/Pages/seeUser.jsp").forward(request, response);
                break;
            default:
                break;

        }

    }
}
