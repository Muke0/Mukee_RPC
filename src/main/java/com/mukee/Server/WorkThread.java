package com.mukee.Server;

import com.mukee.Server.provider.ServiceProvider;
import com.mukee.common.Message.RpcRequest;
import com.mukee.common.Message.RpcResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

@AllArgsConstructor
public class WorkThread implements Runnable {
    private Socket socket;
    private ServiceProvider serviceProvider;
    @Override
    public void run() {
        try{
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());

            RpcRequest c = (RpcRequest)ois.readObject();
            System.out.println("接收到客户端请求");
            RpcResponse r = getRpcResponse(c);
            System.out.println("响应为" + r.toString());
            oos.writeObject(r);
            oos.flush();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public RpcResponse getRpcResponse(RpcRequest rpcRequest) {
        //得到服务名
        String interfaceName = rpcRequest.getInterfaceName();
        //得到服务端相应服务实现类
        Object service = serviceProvider.getService(interfaceName);
        //反射调用方法
        Method method = null;
        try {
            method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsType());
            Object invoke = method.invoke(service,rpcRequest.getParams());
            return RpcResponse.success(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("方法执行错误");
            return RpcResponse.fail(500,"服务器错误");
        }
    }
}
