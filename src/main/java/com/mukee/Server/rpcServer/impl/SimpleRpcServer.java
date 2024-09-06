package com.mukee.Server.rpcServer.impl;

import com.mukee.Server.rpcServer.RpcServer;
import com.mukee.Server.WorkThread;
import com.mukee.Server.provider.ServiceProvider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleRpcServer implements RpcServer {

    private ServiceProvider serviceProvider;
    public SimpleRpcServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void start(int port) {
        try(ServerSocket serverSocket = new ServerSocket(8080)){
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket,serviceProvider)).start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
