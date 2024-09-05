package com.mukee.Server;

import com.mukee.common.Message.RpcRequest;
import com.mukee.common.Message.RpcResponse;
import com.mukee.common.service.UserService;
import com.mukee.common.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String[] args){
        try(ServerSocket serverSocket = new ServerSocket(8080)){
            Socket socket = serverSocket.accept();
            while(true){
                ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                UserService userService = new UserServiceImpl();
                RpcRequest c = (RpcRequest)ois.readObject();
                RpcResponse r = RpcResponse.ok(userService.hello((String) c.getParameters()[0]));
                oos.writeObject(r);
                oos.flush();
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
