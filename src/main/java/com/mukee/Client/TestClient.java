package com.mukee.Client;

import com.mukee.Client.proxy.ClientProxy;
import com.mukee.common.service.UserService;

public class TestClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy();
        UserService userService = clientProxy.getProxy(UserService.class);
        System.out.println(userService.hello("mukee"));
    }
}
