package com.example.spring;

/**
 * Created by Administrator on 2019/3/31/031.
 */
public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("save");
    }

    @Override
    public void delete() {
        System.out.println("delete");
    }

    @Override
    public void update() {
        System.out.println("update");
    }

    @Override
    public void read() {
        System.out.println("read");
    }
}
