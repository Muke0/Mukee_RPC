package com.mukee.Client.proxy;

import com.mukee.Client.IOClient;
import com.mukee.common.Message.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        RpcRequest rpcRequest = RpcRequest.builder()
                .methodName(method.getName())
                .parameters(args)
                .parametersType(method.getParameterTypes())
                .interfaceName(method.getDeclaringClass().getName()).build();
        IOClient ioClient = new IOClient();
        return ioClient.sendRequest(rpcRequest);
    }

    public <T>T getProxy(Class<T> clazz){
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)o;
    }
}
