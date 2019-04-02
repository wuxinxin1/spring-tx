package com.example.spring.transaction.annoservice;

import com.example.spring.transaction.AccountService;
import com.example.spring.transaction.dao.AccountDao;
import org.springframework.transaction.annotation.Transactional;

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
     1.编码
     2.xml形式的aop
     3.注解形式的aop
 * Created by Administrator on 2019/3/31/031.
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    /**
     * 使用注解来申明事务，就会生成代理，使用spring自己的通知，我们只需要标明使用事务的位置，已经使用事务的相关属性（隔离级别，传播性，是否只读）
     * @param from
     * @param to
     * @param money
     */
    @Transactional
    @Override
    public void transferMoney(final int from, final int to, final int money) {
        System.out.println(this.getClass().getName());
        accountDao.subMoney(from,money);
        //int i=1/0;
        accountDao.addMoney(to,money);

    }
}
