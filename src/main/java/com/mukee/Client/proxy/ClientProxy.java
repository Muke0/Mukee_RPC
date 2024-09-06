package com.mukee.Client.proxy;

import com.mukee.Client.rpcClient.RpcClient;
import com.mukee.Client.rpcClient.impl.NettyRpcClient;
import com.mukee.Client.rpcClient.impl.SimpleSocketClient;
import com.mukee.common.Message.RpcRequest;
import com.mukee.common.Message.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        RpcRequest rpcRequest = RpcRequest.builder()
                .methodName(method.getName())
                .params(args)
                .paramsType(method.getParameterTypes())
                .interfaceName(method.getDeclaringClass().getName()).build();
        //SimpleSocketClient simpleSocketClient = new SimpleSocketClient();
        RpcClient nettyRpcClient = new NettyRpcClient("127.0.0.1",8080);
        RpcResponse rpcResponse = nettyRpcClient.sendRequest(rpcRequest);
        return rpcResponse.getData();
    }

    public <T>T getProxy(Class<T> clazz){
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)o;
    }
}
