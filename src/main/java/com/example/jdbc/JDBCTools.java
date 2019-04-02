package com.example.jdbc;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Administrator on 2019/3/29/029.
 */
public class JDBCTools {
    private static final String JDBC_FILE="jdbc.properties";
    private static final String JDBC_DRIVER_CLASS_KEY="driver";
    private static final String JDBC_URL_KEY="url";
    /**
     * 获取一个连接
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        //准备数据
        InputStream resourceAsStream = JDBCTools.class.getClassLoader().getResourceAsStream(JDBC_FILE);
        Properties properties = new Properties();
        properties.load(resourceAsStream);

        //加载驱动
        Class.forName((String) properties.get(JDBC_DRIVER_CLASS_KEY));

        //获取连接
        return DriverManager.getConnection((String) properties.get(JDBC_URL_KEY), properties);
    }


    /**
     * 关闭资源
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet){

        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通用的修改方法，可以执行insert,update,delete语句
     * @param sql
     */
    public static void  update(String sql){
        Connection connection=null;
        Statement statement=null;
        try {
            connection = getConnection();
            statement=connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
    }

    /**
     * 通用的修改方法，可以执行insert,update,delete语句
     * @param sql
     */
    public static void  update(String sql,String...args){
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            connection = getConnection();
            statement=connection.prepareStatement(sql);
            for (int i=0;i<args.length;i++
                 ) {
                statement.setString(i+1,args[i]);
            }

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
    }

    /**
     * 查询方法
     * @throws Exception
     */
    public static void select(String sql){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {
            connection = getConnection();
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String userName = resultSet.getString(2);
                System.out.println("id="+id+":username="+userName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection,statement,resultSet);
        }
    }

    @Test
    public void test() throws Exception{
        //close(getConnection(),null,null);
        //update("insert into user(id,username) values(8,'aaaa')");
        //update("delete  from user where id=6");
        //select("select * from user");
        //update("insert into user(username) values(?)","bbbb");
        //update("delete from user where id=?","5");
        update("update user set username=? where id=?","wangjian","4");
    }

}
