package com.mukee.Server;

import com.mukee.Server.rpcServer.RpcServer;
import com.mukee.Server.rpcServer.impl.NettyRPCServer;
import com.mukee.Server.rpcServer.impl.SimpleRpcServer;
import com.mukee.Server.provider.ServiceProvider;
import com.mukee.common.service.UserService;
import com.mukee.common.service.impl.UserServiceImpl;

public class TestServer {
    public static void main(String[] args){
        UserService userService = new UserServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);

        RpcServer server = new NettyRPCServer(serviceProvider);
        server.start(8080);
    }
}
