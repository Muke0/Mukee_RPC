package com.mukee.Server;

public interface RpcServer {
    //开启监听
    void start(int port);
    void stop();
}

