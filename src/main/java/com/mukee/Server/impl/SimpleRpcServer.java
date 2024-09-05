package com.mukee.Server.impl;

import com.mukee.Server.RpcServer;
import com.mukee.Server.WorkThread;
import com.mukee.Server.provider.ServiceProvider;
import com.mukee.common.Message.RpcRequest;
import com.mukee.common.Message.RpcResponse;
import com.mukee.common.service.UserService;
import com.mukee.common.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
