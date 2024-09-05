package com.mukee.Client;

import com.mukee.common.Message.RpcRequest;
import com.mukee.common.Message.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {
    public RpcResponse sendRequest(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = null;
        try(Socket socket = new Socket("127.0.0.1", 8080)){
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(rpcRequest);
            oos.flush();
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            rpcResponse = (RpcResponse)ois.readObject();
            System.out.println("成功收到服务端响应");
            System.out.println(rpcResponse);
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return rpcResponse;
    }
}
