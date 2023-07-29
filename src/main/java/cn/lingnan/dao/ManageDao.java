package cn.lingnan.dao;



import cn.lingnan.entity.Depositor;

import java.util.List;

public interface ManageDao {

    public abstract List<Depositor> findAll();

    public abstract void add(String username, String password, String name, String tel, String cardid, String pid, String gender);

    public abstract void deleteById(String cardId);

    public abstract Depositor findById(String cardid);

    public abstract void update(String cardId,String name, String tel, String pid, String gender,String address);

}
