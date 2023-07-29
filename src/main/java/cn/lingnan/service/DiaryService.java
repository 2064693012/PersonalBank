package cn.lingnan.service;

import cn.lingnan.entity.Log;

import java.util.List;

public interface DiaryService {
    public boolean insertDiary(String cardID, String log_content);
    public List<Log> getAllDiary(String cardID);
    public List<Log> getAllDiaryByPage(String cardID, int page, int pageSize);
    int getLogCount(String cardID);
}
