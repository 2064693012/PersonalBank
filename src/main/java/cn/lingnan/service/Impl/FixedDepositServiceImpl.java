package cn.lingnan.service.Impl;

import cn.lingnan.dao.FixedDepositDao;
import cn.lingnan.dao.Impl.FixedDepositDaoImpl;
import cn.lingnan.dao.Impl.LoginDaoImpl;
import cn.lingnan.entity.FixedDeposit;
import cn.lingnan.service.FixedDepositService;

import java.util.List;

public class FixedDepositServiceImpl implements FixedDepositService {
    private FixedDepositDao fixedDepositDao = new FixedDepositDaoImpl();
    @Override
    public boolean addFixedDeposit(FixedDeposit fixedDeposit) {
        return fixedDepositDao.addFixedDeposit(fixedDeposit);
    }

    @Override
    public boolean updateFixedDeposit(int id,String cardID, FixedDeposit fixedDeposit) {
        return fixedDepositDao.updateFixedDeposit(id,cardID,fixedDeposit);
    }

    @Override
    public FixedDeposit getFixedDepositById(String cardID) {
        return fixedDepositDao.getFixedDepositById(cardID);
    }

    @Override
    public boolean deleteFixedDeposit(int id,String cardID) {
        return fixedDepositDao.deleteFixedDeposit(id,cardID);
    }


    @Override
    public List<FixedDeposit> getAllFixedDeposits() {
        return fixedDepositDao.getAllFixedDeposits();
    }
}
