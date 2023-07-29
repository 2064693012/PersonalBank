package cn.lingnan.dao.Impl;


import cn.lingnan.entity.Account;
import cn.lingnan.entity.Depositor;
import cn.lingnan.dao.ServiceDao;
import cn.lingnan.util.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ServiceDaoImpl implements ServiceDao {




    @Override
    public Account findAllOfAccount(Depositor depositor) {

        Account account = null;
        String card = depositor.getCardid();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "select * from account where cardId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,card);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String cardId = resultSet.getString(1);
                String status = resultSet.getString(2);
                String credit = resultSet.getString(3);
                Double balance = resultSet.getDouble(4);

                account = new Account(cardId,status,credit,balance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }

        return account;
    }

    @Override
    public Account findAllOfAccount(String cardId) {
        Account account = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "select * from account where cardId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,cardId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String status = resultSet.getString(2);
                String credit = resultSet.getString(3);
                Double balance = resultSet.getDouble(4);

                account = new Account(cardId,status,credit,balance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }

        return account;
    }
}
