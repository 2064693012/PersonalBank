package cn.lingnan.service.Impl;

import cn.lingnan.dao.Impl.LoginDaoImpl;
import cn.lingnan.entity.Depositor;
import cn.lingnan.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private LoginDaoImpl loginDao = new LoginDaoImpl();
    @Override
    public boolean isLogin(String tel, String password) {
        return loginDao.isLogin(tel,password);
    }

    @Override
    public boolean isLoginOfAdmin(String username, String password) {
        return loginDao.isLoginOfAdmin(username,password);
    }

    @Override
    public Depositor findAllOfDepositor(String username) {
        return loginDao.findAllOfDepositor(username);
    }
}
