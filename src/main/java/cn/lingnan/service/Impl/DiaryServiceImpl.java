package cn.lingnan.service.Impl;

import cn.lingnan.dao.DiaryDao;
import cn.lingnan.dao.Impl.DiaryDaoImpl;
import cn.lingnan.entity.Log;
import cn.lingnan.service.DiaryService;

import java.util.List;

public class DiaryServiceImpl  implements DiaryService {
    private DiaryDao diaryDao = new DiaryDaoImpl();
    @Override
    public boolean insertDiary(String cardID, String log_content) {
        return diaryDao.insertDiary(cardID,log_content);
    }

    @Override
    public List<Log> getAllDiary(String cardID) {
        return diaryDao.getAllDiary(cardID);
    }

    @Override
    public List<Log> getAllDiaryByPage(String cardID, int page, int pageSize) {
        return diaryDao.getAllDiaryByPage(cardID,page,pageSize);
    }

    @Override
    public int getLogCount(String cardID) {
        return diaryDao.getLogCount(cardID);
    }
}
