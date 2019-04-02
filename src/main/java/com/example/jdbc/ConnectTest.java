package com.example.jdbc;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 测试数据库的连接
 * Created by Administrator on 2019/3/28/028.
 */
public class ConnectTest {

    /**
     * 测试怎样从classpath路径下获取文件
     */
    @Test
    public void test0() throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);

        System.out.println(properties.get("driver"));
        System.out.println(properties.get("url"));
        System.out.println(properties.get("username"));
        System.out.println(properties.get("password"));


    }

    /**
     * 通过Driver获取连接
     * 1.先获取驱动
     * 2.准备连接数据库的基本参数
     * 3.使用驱动获取连接
     */
    @Test
    public void  test1() throws Exception {
        //先获取到驱动
        Driver driver = new  com.mysql.jdbc.Driver();
        //获取连接需要的数据

        Properties properties1 = new Properties();
        properties1.put("user","root");
        properties1.put("password","root");
        String url="jdbc:mysql://localhost:3306/spring";
        //获取连接
        Connection connect = driver.connect(url, properties1);

        System.out.println(connect);
    }

    /**
     * 编写一个适合所有数据库通用的获取数据库连接的测试方法
     * @throws Exception
     */
    @Test
    public void  test2() throws Exception {
        //将数据库配置放在配置文件中，然后加载进来
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);

        //加载对应的驱动
        Driver driver= (Driver) Class.forName((String) properties.get("driver")).newInstance();

        //获取连接
        Connection connection = driver.connect((String) properties.get("url"), properties);

        System.out.println(connection);
    }


    /**
     * 使用DriverManager来获取连接，可以同时管理多个driver
     */
    @Test
    public void  test3() throws Exception {
        //将数据库配置放在配置文件中，然后加载进来
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);

        //加载对应的驱动,有静态代码执行，将当前的驱动添加到DriverManager
        Class.forName((String) properties.get("driver"));

        //获取连接
        Connection connection = DriverManager.getConnection((String) properties.get("url"), properties);

        System.out.println(connection);
    }

}
