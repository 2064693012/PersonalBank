package cn.lingnan.test;



import cn.lingnan.util.JDBCTools;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLTest {

    public static void main(String[] args) {

        try {
            Connection connection = JDBCTools.getConnection();
            System.out.println(connection);
            JDBCTools.release(connection,null,null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
