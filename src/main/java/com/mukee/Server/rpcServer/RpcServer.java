package com.mukee.Server.rpcServer;

public interface RpcServer {
    //开启监听
    void start(int port);
    void stop();
}

