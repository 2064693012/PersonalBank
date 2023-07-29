package cn.lingnan.dao.Impl;



import cn.lingnan.entity.Depositor;
import cn.lingnan.dao.LoginDao;
import cn.lingnan.util.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDaoImpl implements LoginDao {


    //判断用户登陆，使用手机号码+密码登陆
    @Override
    public boolean isLogin(String tel, String password) {

        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            System.out.println("Connect MYSQL: " + connection);
            String sql = "select password from depositor where tel = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,tel);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String checkpassword = resultSet.getString(1);
                System.out.println("Correct password:" + checkpassword);
                if(checkpassword.equals(password)){
                    flag = true;
                }else{
                    System.out.println("Your Password is wrong!!!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return flag;

    }

    @Override
    public boolean isLoginOfAdmin(String username, String password) {

        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            System.out.println("Connect MYSQL: " + connection);
            String sql = "select AdminPassword from bankadmin where AdminId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String checkpassword = resultSet.getString(1);
                System.out.println("Correct password:" + checkpassword);
                if(checkpassword.equals(password)){
                    flag = true;
                }else{
                    System.out.println("Your Password is wrong!!!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return flag;
    }


    //通过传过来的登陆信息找到整个用户个人信息表
    @Override
    public Depositor findAllOfDepositor(String username) {

        Depositor depositor = null;

//        if(isLogin){
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = JDBCTools.getConnection();
                String sql = "select * from depositor where tel = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,username);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String depositorId = resultSet.getString(1);
                    String nickname = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    String name = resultSet.getString(4);
                    String tel = resultSet.getString(5);
                    String cardid = resultSet.getString(6);
                    String pid = resultSet.getString(7);
                    String gender = resultSet.getString(8);
                    String address = resultSet.getString(9);
                    depositor = new Depositor(nickname,password,name,tel,cardid,pid,gender,address);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JDBCTools.release(connection,preparedStatement,resultSet);
            }

        return depositor;
    }

}
