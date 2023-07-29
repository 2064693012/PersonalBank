package cn.lingnan.dao.Impl;

import cn.lingnan.dao.FixedDepositDao;
import cn.lingnan.entity.FixedDeposit;
import cn.lingnan.util.JDBCTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FixedDepositDaoImpl implements FixedDepositDao {
    @Override
    public boolean addFixedDeposit(FixedDeposit fixedDeposit) {
        boolean success = false;
        String sql = "INSERT INTO FixedDeposit (account_number, start_date, end_date, deposit_amount, deposit_type, interest_rate, maturity_amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = JDBCTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fixedDeposit.getcardID());
            preparedStatement.setDate(2, new java.sql.Date(fixedDeposit.getStartDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(fixedDeposit.getEndDate().getTime()));
            preparedStatement.setDouble(4, fixedDeposit.getDepositAmount());
            preparedStatement.setString(5, fixedDeposit.getDepositType());
            preparedStatement.setDouble(6, fixedDeposit.getInterestRate());
            preparedStatement.setDouble(7, fixedDeposit.getMaturityAmount());

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

    @Override
    public boolean updateFixedDeposit(int id,String cardID, FixedDeposit fixedDeposit) {
        boolean success = false;
        String sql = "UPDATE FixedDeposit SET start_date=?, end_date=?, deposit_amount=?, deposit_type=?, interest_rate=?, maturity_amount=? WHERE account_number=? and id=?";
        try (Connection connection = JDBCTools.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(fixedDeposit.getStartDate().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(fixedDeposit.getEndDate().getTime()));
            preparedStatement.setDouble(3, fixedDeposit.getDepositAmount());
            preparedStatement.setString(4, fixedDeposit.getDepositType());
            preparedStatement.setDouble(5, fixedDeposit.getInterestRate());
            preparedStatement.setDouble(6, fixedDeposit.getMaturityAmount());
            preparedStatement.setString(7, cardID);
            preparedStatement.setInt(8, id);

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

    @Override
    public FixedDeposit getFixedDepositById(String cardID) {
        FixedDeposit fixedDeposit = null;
        String sql = "SELECT * FROM FixedDeposit WHERE account_number=?";
        try (Connection connection = JDBCTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cardID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                java.sql.Date startDate = resultSet.getDate("start_date");
                java.sql.Date endDate = resultSet.getDate("end_date");
                double depositAmount = resultSet.getDouble("deposit_amount");
                String depositType = resultSet.getString("deposit_type");
                double interestRate = resultSet.getDouble("interest_rate");
                double maturityAmount = resultSet.getDouble("maturity_amount");

                fixedDeposit = new FixedDeposit(id, cardID, startDate, endDate, depositAmount, depositType, interestRate, maturityAmount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fixedDeposit;
    }

    @Override
    public boolean deleteFixedDeposit(int id,String cardID) {
        boolean success = false;
        String sql = "DELETE FROM FixedDeposit WHERE account_number=? and id=?";
        try (Connection connection = JDBCTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cardID);
            preparedStatement.setInt(2, id);

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


    @Override
    public List<FixedDeposit> getFixedDeposits(String cardID,String depositType) {
        List<FixedDeposit> fixedDeposits = new ArrayList<>();
        String sql = "SELECT * FROM FixedDeposit where account_number=? and deposit_type=?";
        try (Connection connection = JDBCTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cardID);
            preparedStatement.setString(2, depositType);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                java.sql.Date startDate = resultSet.getDate("start_date");
                java.sql.Date endDate = resultSet.getDate("end_date");
                double depositAmount = resultSet.getDouble("deposit_amount");
                double interestRate = resultSet.getDouble("interest_rate");
                double maturityAmount = resultSet.getDouble("maturity_amount");

                FixedDeposit fixedDeposit = new FixedDeposit(id, cardID, startDate, endDate, depositAmount, depositType, interestRate, maturityAmount);
                fixedDeposits.add(fixedDeposit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fixedDeposits;
    }

    @Override
    public List<FixedDeposit> getAllFixedDeposits() {
        List<FixedDeposit> fixedDeposits = new ArrayList<>();
        String sql = "SELECT * FROM FixedDeposit";
        try (Connection connection = JDBCTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cardID = resultSet.getString("account_number");
                java.sql.Date startDate = resultSet.getDate("start_date");
                java.sql.Date endDate = resultSet.getDate("end_date");
                double depositAmount = resultSet.getDouble("deposit_amount");
                String depositType = resultSet.getString("deposit_type");
                double interestRate = resultSet.getDouble("interest_rate");
                double maturityAmount = resultSet.getDouble("maturity_amount");

                FixedDeposit fixedDeposit = new FixedDeposit(id, cardID, startDate, endDate, depositAmount, depositType, interestRate, maturityAmount);
                fixedDeposits.add(fixedDeposit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fixedDeposits;
    }
}
