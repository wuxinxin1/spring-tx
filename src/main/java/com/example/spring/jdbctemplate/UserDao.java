package com.example.spring.jdbctemplate;

import java.util.List;

/**
 * Created by Administrator on 2019/3/31/031.
 */
public interface UserDao {
    void add(User user);
    void delete(User user);
    void update(User user);
    User getUserById(int id);
    int  getAccount();
    List<User> getAllUser();
}
