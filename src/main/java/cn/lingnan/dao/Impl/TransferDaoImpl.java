package cn.lingnan.dao.Impl;



import cn.lingnan.entity.Depositor;
import cn.lingnan.entity.Transfer;
import cn.lingnan.dao.TransferDao;
import cn.lingnan.util.JDBCTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferDaoImpl implements TransferDao {


    @Override
    public boolean transfer(String mycardId, double amountTransferred, String payee, String cardIdOfPayee, String remarks) {
        boolean flag = transaction(mycardId, amountTransferred, cardIdOfPayee);
        if (flag) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            Timestamp datetime = new Timestamp(System.currentTimeMillis()); // Get the current date and time as Timestamp

            try {
                connection = JDBCTools.getConnection();
                String sql = "insert into trade(cardId, AmountTransferred, payee, cardIdOfPayee, remarks, transferTime) values(?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, mycardId);
                preparedStatement.setDouble(2, amountTransferred);
                preparedStatement.setString(3, payee);
                preparedStatement.setString(4, cardIdOfPayee);
                preparedStatement.setString(5, remarks);
                preparedStatement.setTimestamp(6, datetime); // Use the Timestamp object directly
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JDBCTools.release(connection, preparedStatement, resultSet);
            }
        } else {
            System.out.println("账户余额不足，无法完成交易！！！");
        }

        return flag;
    }

    @Override
    public List<Transfer> findAllOfTransfer(Depositor depositor) {

        String card = depositor.getCardid();
        System.out.println(card);
        List<Transfer> list = new ArrayList<>();
        Transfer transfer = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "select * from trade where cardId = ? or  cardIdOfPayee=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,card);
            preparedStatement.setString(2,card);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String mycardId = resultSet.getString(1);
                Double amountTransferred = resultSet.getDouble(2);
                String payee = resultSet.getString(3);
                String cardidOfPayee = resultSet.getString(4);
                String remarks = resultSet.getString(5);
                Timestamp datetime = resultSet.getTimestamp(6);


                System.out.println(mycardId+amountTransferred+payee+cardidOfPayee+remarks+datetime);
                transfer = new Transfer(mycardId,amountTransferred,payee,cardidOfPayee,remarks,datetime);
                list.add(transfer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }

        return list;
    }

    @Override
    public boolean transaction(String cardId, double money,String cardIdOfPayee) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Double balance = -1.0;
        boolean flag = false;

        try {
            connection = JDBCTools.getConnection();
            String sql = "select balance from account where cardID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,cardId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                balance = resultSet.getDouble(1);
                System.out.println("转账人余额："+ balance);
            }

            if(money <= balance && money > 0){
                double sum = balance - money;
                sql = " update account set balance = ? where cardID = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1,sum);
                preparedStatement.setString(2,cardId);
                preparedStatement.executeUpdate();
                flag = true;
                System.out.println("转账成功！");
            }else{
                System.out.println("转账失败！");
            }

            if(cardIdOfPayee != null && flag == true){

                sql = "select balance from account where cardID = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,cardIdOfPayee);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    balance = resultSet.getDouble(1);
                    System.out.println("收款人余额"+ balance);
                }

                double sum = balance + money;
                sql = " update account set balance = ? where cardID = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1,sum);
                preparedStatement.setString(2,cardIdOfPayee);
                preparedStatement.executeUpdate();
                System.out.println("收款成功！");

            }else {
                System.out.println("收款失败!");
            }




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }

        return flag;

    }

    public boolean deposit(String cardId, double amount) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean success = false;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT balance FROM account WHERE cardID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cardId);
            resultSet = preparedStatement.executeQuery();

            double balance = 0;
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
                System.out.println("当前余额：" + balance);
            }

            if (amount > 0) {
                double newBalance = balance + amount;
                sql = "UPDATE account SET balance = ? WHERE cardID = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, newBalance);
                preparedStatement.setString(2, cardId);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    success = true;
                    System.out.println("存款成功！");
                    transfer(cardId,amount,"存款", cardId, "存款");
                }
            } else {
                System.out.println("存款金额必须大于0！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }

        return success;
    }

    public boolean withdraw(String cardId, double amount) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean success = false;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT balance FROM account WHERE cardID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cardId);
            resultSet = preparedStatement.executeQuery();

            double balance = 0;
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
                System.out.println("当前余额：" + balance);
            }

            if (amount > 0 && amount <= balance) {
                double newBalance = balance - amount;
                sql = "UPDATE account SET balance = ? WHERE cardID = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, newBalance);
                preparedStatement.setString(2, cardId);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    success = true;
                    System.out.println("取款成功！");
                }
            } else {
                System.out.println("取款金额必须大于0且不超过当前余额！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }

        return success;
    }


}
