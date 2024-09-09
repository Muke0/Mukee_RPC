package com.mukee.Server.provider;

import com.mukee.Server.serviceRegister.ServiceRegister;
import com.mukee.Server.serviceRegister.impl.ZKServiceRegister;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    //集合中存放服务的实例
    private Map<String,Object> interfaceProvider;

    private int port;
    private String host;

    private ServiceRegister serviceRegister;

    public ServiceProvider(String host, int port){
        this.host = host;
        this.port = port;
        this.interfaceProvider = new HashMap<>();
        this.serviceRegister = new ZKServiceRegister();
    }
    //本地注册服务

    public void provideServiceInterface(Object service){
        String serviceName = service.getClass().getName();
        Class<?>[] interfaceName = service.getClass().getInterfaces();

        //提供接口到服务的映射
        for (Class<?> clazz:interfaceName){
            interfaceProvider.put(clazz.getName(),service);
            //在注册中心注册服务
            serviceRegister.register(clazz.getName(),new InetSocketAddress(host,port));
        }

    }
    //获取服务实例
    public Object getService(String interfaceName){
        return interfaceProvider.get(interfaceName);
    }
}
