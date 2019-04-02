package com.example.spring.transaction.dao;

/**
 * Created by Administrator on 2019/3/31/031.
 */
public interface AccountDao {
    //加钱
    void addMoney(int id,int money);
    //减钱
    void subMoney(int id,int money);
}
