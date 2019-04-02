package com.example.spring.transaction.dao;

import com.example.spring.transaction.dao.AccountDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by Administrator on 2019/3/31/031.
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

    @Override
    public void addMoney(int id, int money) {
        String sql="update account set money=money+? where id=?";
        getJdbcTemplate().update(sql,money,id);
    }

    @Override
    public void subMoney(int id, int money) {
        String sql="update account set money=money-? where id=?";
        getJdbcTemplate().update(sql,money,id);
    }
}
