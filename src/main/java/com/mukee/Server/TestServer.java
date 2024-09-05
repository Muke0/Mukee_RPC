package com.mukee.Server;

import com.mukee.Server.impl.SimpleRpcServer;
import com.mukee.Server.provider.ServiceProvider;
import com.mukee.common.service.UserService;
import com.mukee.common.service.impl.UserServiceImpl;

public class TestServer {
    public static void main(String[] args){
        UserService userService = new UserServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);

        SimpleRpcServer server = new SimpleRpcServer(serviceProvider);
        server.start(8080);
    }
}
