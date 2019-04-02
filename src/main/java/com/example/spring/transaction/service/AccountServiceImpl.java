package com.example.spring.transaction.service;

import com.example.spring.transaction.AccountService;
import com.example.spring.transaction.dao.AccountDao;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 操作事务注意的三项
 *
 * 1.@See  PlatformTransactionManager ，这个接口有三个方法(封装了对事务的基本操作)
 *   TransactionStatus getTransaction(TransactionDefinition var1) throws TransactionException;  //开启一个事物
 *   void commit(TransactionStatus var1) throws TransactionException; //提交事务
 *   void rollback(TransactionStatus var1) throws TransactionException; //回滚事务
 *
 * 2.TransactionDefinition 配置事务详情（对事务的配置）
 *   1.配置事务隔离级别
 *      int ISOLATION_READ_UNCOMMITTED = 1;  未提交
        int ISOLATION_READ_COMMITTED = 2;    已提交
        int ISOLATION_REPEATABLE_READ = 4;   可重复读
        int ISOLATION_SERIALIZABLE = 8;      串行化
     2.配置事务传播性
        int PROPAGATION_REQUIRED = 0;
        int PROPAGATION_SUPPORTS = 1;
        int PROPAGATION_MANDATORY = 2;
        int PROPAGATION_REQUIRES_NEW = 3;
        int PROPAGATION_NOT_SUPPORTED = 4;
        int PROPAGATION_NEVER = 5;
        int PROPAGATION_NESTED = 6;
      3.配置是否为可读事务
         boolean isReadOnly();
 3.spring事务管理方式
     1.编码(transactionTemplate是使用编码的方式)
     2.xml形式的aop
     3.注解形式的aop
 * Created by Administrator on 2019/3/31/031.
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    //TransactionTemplate继承了DefaultTransactionDefinition，
    // 所以通过操作TransactionTemplate的相关TransactionDefinition的方法可以进行事务配置
    //TransactionTemplate里面组合了PlatformTransactionManager，所以可以进行开启，提交，回滚操作
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transferMoney(final int from, final int to, final int money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult(){

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountDao.subMoney(from,money);
                int i=1/0;
                accountDao.addMoney(to,money);
            }
        });

    }
}
