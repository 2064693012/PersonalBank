package cn.lingnan.test;

import cn.lingnan.dao.FixedDepositDao;
import cn.lingnan.dao.Impl.FixedDepositDaoImpl;
import cn.lingnan.entity.FixedDeposit;
import cn.lingnan.service.FixedDepositService;
import cn.lingnan.service.Impl.FixedDepositServiceImpl;

import java.sql.Date;
import java.util.List;

public class FixedDepositDaoImplTest {

    public static void main(String[] args) {
        FixedDepositDaoImplTest test = new FixedDepositDaoImplTest();
//        test.testAddFixedDeposit();
//        test.testUpdateFixedDeposit();
//        test.testGetFixedDepositById();
//        test.testGetAllFixedDeposits();
//        test.testGetFixedDeposits();
        test.testDeleteFixedDeposit();


    }

    public void testAddFixedDeposit() {
        FixedDepositDao fixedDepositDao = new FixedDepositDaoImpl();
        FixedDeposit fixedDeposit = new FixedDeposit("6217280937373828200", Date.valueOf("2023-07-01"),
                Date.valueOf("2023-08-01"), 5000.0, "活期", 0.05, 5250.0);
        boolean result = fixedDepositDao.addFixedDeposit(fixedDeposit);
        System.out.println("Add Fixed Deposit - Result: " + result);
    }

    public void testUpdateFixedDeposit() {
        FixedDepositDaoImpl fixedDepositDao = new FixedDepositDaoImpl();
        FixedDeposit fixedDeposit = fixedDepositDao.getFixedDepositById("6217280937373828200");
        if (fixedDeposit != null) {
            fixedDeposit.setDepositAmount(6000.0);
            boolean result = fixedDepositDao.updateFixedDeposit(2,"6217280937373828200", fixedDeposit);
            System.out.println("Update Fixed Deposit - Result: " + result);
        }
    }

    public void testGetFixedDepositById() {
        FixedDepositDaoImpl fixedDepositDao = new FixedDepositDaoImpl();
        FixedDeposit fixedDeposit = fixedDepositDao.getFixedDepositById("6217280937373828274");
        if (fixedDeposit != null) {
            System.out.println("Get Fixed Deposit By Id: " + fixedDeposit.toString());
        } else {
            System.out.println("Fixed Deposit not found.");
        }
    }

    public void testDeleteFixedDeposit() {
        FixedDepositService fixedDepositDao = new FixedDepositServiceImpl();
        boolean result = fixedDepositDao.deleteFixedDeposit(3,"6217280937373828200");
        System.out.println("Delete Fixed Deposit - Result: " + result);
    }

    public void testGetAllFixedDeposits() {
        FixedDepositDaoImpl fixedDepositDao = new FixedDepositDaoImpl();
        List<FixedDeposit> fixedDeposits = fixedDepositDao.getAllFixedDeposits();
        if (!fixedDeposits.isEmpty()) {
            for (FixedDeposit fixedDeposit : fixedDeposits) {
                System.out.println(fixedDeposit.toString());
            }
        } else {
            System.out.println("No Fixed Deposits found.");
        }
    }
    public void testGetFixedDeposits() {
        FixedDepositDaoImpl fixedDepositDao = new FixedDepositDaoImpl();
        List<FixedDeposit> fixedDeposits = fixedDepositDao.getFixedDeposits("6217280742900642000","活期");
        if (!fixedDeposits.isEmpty()) {
            for (FixedDeposit fixedDeposit : fixedDeposits) {
                System.out.println("活期查找");
                System.out.println(fixedDeposit.toString());
            }
        } else {
            System.out.println("No Fixed Deposits found.");
        }
    }
}
