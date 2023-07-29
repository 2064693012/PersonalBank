package cn.lingnan.dao.Impl;

import cn.lingnan.dao.DiaryDao;
import cn.lingnan.entity.Log;
import cn.lingnan.util.JDBCTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaryDaoImpl implements DiaryDao {

    public boolean insertDiary(String cardID, String log_content) {
        boolean success = false;
        String sql = "INSERT INTO log (cardID, log_content, log_time) VALUES (?, ?, ?)";
        Timestamp datetime = new Timestamp(System.currentTimeMillis()); // Get the current date and time as Timestamp
        try (Connection connection = JDBCTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cardID);
            preparedStatement.setString(2, log_content);
            preparedStatement.setTimestamp(3, datetime);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    public List<Log> getAllDiary(String cardID) {
        List<Log> logs = new ArrayList<>();
        String sql = "SELECT * FROM log WHERE cardID = ? ORDER BY log_time DESC";
        try (Connection connection = JDBCTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cardID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int log_id = resultSet.getInt("log_id");
                String log_content = resultSet.getString("log_content");
                Timestamp log_time = resultSet.getTimestamp("log_time");

                Log log = new Log(log_id, cardID, log_content, new Timestamp(log_time.getTime()));
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return logs;
    }

    // DiaryDaoImpl.java
    @Override
    public int getLogCount(String cardID) {
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT COUNT(*) FROM log WHERE cardID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cardID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }

        return count;
    }

    public List<Log> getAllDiaryByPage(String cardID, int page, int pageSize) {
        List<Log> logs = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM log WHERE cardID = ? ORDER BY log_time DESC LIMIT ?, ?";
            int startIndex = (page - 1) * pageSize;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cardID);
            preparedStatement.setInt(2, startIndex);
            preparedStatement.setInt(3, pageSize);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int log_id = resultSet.getInt("log_id");
                String log_content = resultSet.getString("log_content");
                Timestamp log_time = resultSet.getTimestamp("log_time");

                Log log = new Log(log_id, cardID, log_content, new Timestamp(log_time.getTime()));
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }

        return logs;
    }

}
