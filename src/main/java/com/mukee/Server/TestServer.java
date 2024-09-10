package com.mukee.Server;

import com.mukee.Server.provider.ServiceProvider;
import com.mukee.Server.rpcServer.RpcServer;
import com.mukee.Server.rpcServer.impl.NettyRPCServer;
import com.mukee.common.service.UserService;
import com.mukee.common.service.impl.UserServiceImpl;

public class TestServer {
    public static void main(String[] args){
        String serverHost = "127.0.0.1";
        int serverPort = 9998;
        UserService userService = new UserServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider(serverHost,serverPort);
        serviceProvider.provideServiceInterface(userService);

        RpcServer server = new NettyRPCServer(serviceProvider);
        System.out.println("服务器启动成功");
        server.start(serverPort);
    }
}
