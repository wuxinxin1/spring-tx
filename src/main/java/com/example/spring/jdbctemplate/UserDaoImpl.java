package com.example.spring.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * dao层只是封装单个数据库操作的，供service层组合调用
 * JdbcTemplate只是对底层jdbc操作的封装
 * Created by Administrator on 2019/3/31/031.
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(User user) {
        String sql="insert into user(username) values(?)";
        jdbcTemplate.update(sql,user.getUserName());
    }

    @Override
    public void delete(User user) {
        String sql="delete user where id=?";
        jdbcTemplate.update(sql,user.getId());
    }

    @Override
    public void update(User user) {
        String sql="update user set username=? where id=?";
        jdbcTemplate.update(sql,user.getUserName(),user.getId());
    }

    @Override
    public User getUserById(int id) {
        String sql="select * from user where id=?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user=new User();

                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                return user;
            }
        }, id);
    }

    @Override
    public int getAccount() {
        return 0;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }
}
