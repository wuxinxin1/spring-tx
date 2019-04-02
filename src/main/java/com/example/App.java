package com.example;

import com.example.spring.UserService;
import com.example.spring.jdbctemplate.User;
import com.example.spring.jdbctemplate.UserDao;
import com.example.spring.transaction.AccountService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Test
    public void test01(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-context.xml");

        UserService bean = classPathXmlApplicationContext.getBean(UserService.class);


        bean.save();

        print(classPathXmlApplicationContext);
    }

    @Test
    public void test02(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-context-anno.xml");

        UserService bean = classPathXmlApplicationContext.getBean(UserService.class);


        bean.save();

        print(classPathXmlApplicationContext);
    }

    @Test
    public void test03(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-context-jdbctemplate.xml");

        UserDao userDao = (UserDao)classPathXmlApplicationContext.getBean("userDao");

        /*User user = new User();

        user.setUserName("tom");

        userDao.add(user);*/

        User user = userDao.getUserById(3);
        System.out.println(user);

        print(classPathXmlApplicationContext);
    }

    @Test
    public void test04(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-context-transaction-template.xml");

        AccountService accountService = (AccountService)classPathXmlApplicationContext.getBean(AccountService.class);

        accountService.transferMoney(1,2,100);

        print(classPathXmlApplicationContext);
    }


    @Test
    public void test05(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-context-transaction-xmlaop.xml");

        AccountService accountService = (AccountService)classPathXmlApplicationContext.getBean(AccountService.class);

        accountService.transferMoney(1,2,100);

        print(classPathXmlApplicationContext);
    }

    @Test
    public void test06(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-context-transaction-annoaop.xml");

        AccountService accountService = (AccountService)classPathXmlApplicationContext.getBean(AccountService.class);

        accountService.transferMoney(1,2,100);

        print(classPathXmlApplicationContext);
    }





    private void print(ClassPathXmlApplicationContext classPathXmlApplicationContext){
        String[] beanDefinitionNames = classPathXmlApplicationContext.getBeanDefinitionNames();
        for (String name:beanDefinitionNames){
            System.out.println(name+"==="+classPathXmlApplicationContext.getType(name));
        }
    }
}
