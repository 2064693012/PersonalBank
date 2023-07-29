package cn.lingnan.service;

import cn.lingnan.entity.Depositor;

public interface LoginService {
    public abstract boolean isLogin(String tel, String password);

    //验证管理员密码是否正确
    public abstract boolean isLoginOfAdmin(String username,String password);

    //找到所有的个人信息
    public abstract Depositor findAllOfDepositor(String username);
}
