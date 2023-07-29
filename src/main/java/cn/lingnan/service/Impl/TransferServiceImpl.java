package cn.lingnan.service.Impl;

import cn.lingnan.dao.Impl.TransferDaoImpl;
import cn.lingnan.entity.Depositor;
import cn.lingnan.entity.Transfer;
import cn.lingnan.service.TransferService;

import java.util.List;

public class TransferServiceImpl implements TransferService {
    TransferDaoImpl transferDao = new TransferDaoImpl();
    @Override
    public boolean transfer(String mycardId, double amountTransferred, String payee, String cardIdOfPayee, String remarks) {
        return transferDao.transfer(mycardId,amountTransferred,payee,cardIdOfPayee,remarks);
    }

    @Override
    public List<Transfer> findAllOfTransfer(Depositor depositor) {
        return transferDao.findAllOfTransfer(depositor);
    }

    @Override
    public boolean transaction(String cardId, double money, String cardIdOfPayee) {
        return transferDao.transaction(cardId,money,cardIdOfPayee);
    }

    @Override
    public boolean deposit(String cardId, double amount) {
        return transferDao.deposit(cardId,amount);
    }

    @Override
    public boolean withdraw(String cardId, double amount) {
        return false;
    }
}
