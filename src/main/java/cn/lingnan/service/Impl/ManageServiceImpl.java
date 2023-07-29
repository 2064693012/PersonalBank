package cn.lingnan.service.Impl;

import cn.lingnan.dao.Impl.ManageDaoImpl;
import cn.lingnan.entity.Depositor;
import cn.lingnan.service.ManageService;

import java.util.List;

public class ManageServiceImpl implements ManageService {
    private ManageDaoImpl manageDao = new ManageDaoImpl();
    @Override
    public List<Depositor> findAll() {
        return manageDao.findAll();
    }

    @Override
    public void add(String username, String password, String name, String tel, String cardid, String pid, String gender) {
        manageDao.add(username, password, name, tel, cardid, pid, gender);
    }

    @Override
    public void deleteById(String cardId) {
        manageDao.deleteById(cardId);
    }

    @Override
    public Depositor findById(String cardid) {
        return manageDao.findById(cardid);
    }

    @Override
    public void update(String cardId, String name, String tel, String pid, String gender, String address) {
         manageDao.update(cardId,name,tel,pid,gender,address);
    }
}
