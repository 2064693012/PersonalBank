package cn.lingnan.dao;


import cn.lingnan.entity.Depositor;
import cn.lingnan.entity.Transfer;

import java.util.List;

public interface TransferDao {

    //转账数据存入数据库
    public abstract boolean transfer(String mycardId, double amountTransferred, String payee, String cardIdOfPayee, String remarks);

    //查询交易明细
    public abstract List<Transfer> findAllOfTransfer(Depositor depositor);

    //交易数据处理
    public abstract boolean transaction(String cardId, double money,String cardIdOfPayee);
    public boolean deposit(String cardId, double amount);
    public boolean withdraw(String cardId, double amount);
}