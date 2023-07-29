package cn.lingnan.service.Impl;

import cn.lingnan.dao.Impl.ServiceDaoImpl;
import cn.lingnan.dao.ServiceDao;
import cn.lingnan.entity.Account;
import cn.lingnan.entity.Depositor;
import cn.lingnan.service.ServiceService;

public class ServiceServiceImpl implements ServiceService {
    private ServiceDao serviceDao = new ServiceDaoImpl();
    @Override
    public Account findAllOfAccount(Depositor depositor) {
        return serviceDao.findAllOfAccount(depositor);
    }

    @Override
    public Account findAllOfAccount(String cardId) {
        return serviceDao.findAllOfAccount(cardId);
    }
}
