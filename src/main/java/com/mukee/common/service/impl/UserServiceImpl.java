package com.mukee.common.service.impl;

import com.mukee.common.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
