package cn.lingnan.dao;

import cn.lingnan.entity.Log;

import java.util.List;

public interface DiaryDao {

    //转账数据存入数据库
    public boolean insertDiary(String cardID, String log_content);
    public List<Log> getAllDiary(String cardID);
    public List<Log> getAllDiaryByPage(String cardID, int page, int pageSize);
    int getLogCount(String cardID);
}
