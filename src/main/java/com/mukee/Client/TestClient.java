package com.mukee.Client;

import com.mukee.common.Message.RpcRequest;
import com.mukee.common.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1", 8080)){
            while(true){
                Object[] o = new Object[1];
                o[0] = "mukee";
                Class c = UserService.class;
                Method method = c.getMethod("hello", String.class);
                RpcRequest rpcRequest = RpcRequest.builder().parameters(o).methodName(method.getName()).parametersType(method.getParameterTypes()).build();
                ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(rpcRequest);
                oos.flush();
                ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                System.out.println(ois.readObject());
                System.out.println("传输");
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
