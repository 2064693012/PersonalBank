package cn.lingnan.service.Impl;

import cn.lingnan.dao.Impl.ModifyDaoImpl;
import cn.lingnan.entity.Depositor;
import cn.lingnan.service.ModifyService;

import java.sql.Timestamp;

public class ModifyServiceImpl implements ModifyService {
    private ModifyDaoImpl modifyDao = new ModifyDaoImpl();
    @Override
    public void modifyInfo(String tel, String gender, String address) {
        modifyDao.modifyInfo(tel,gender,address);
    }

    @Override
    public boolean modifyPassword(Depositor depositor, String oldPassword, String newPassword, String confirmPassword) {
        return modifyDao.modifyPassword(depositor,oldPassword,newPassword,confirmPassword);
    }

    @Override
    public void deleteByCardId(Timestamp date) {
        modifyDao.deleteByCardId(date);
    }
}
