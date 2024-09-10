package com.mukee.common.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RpcResponse implements Serializable {
    private int code;
    private String message;
    //更新：加入传输数据的类型，以便在自定义序列化器中解析
    private Class<?> dataType;
    private Object data;

    public static RpcResponse success(Object data){
        return RpcResponse.builder().code(200).dataType(data.getClass()).data(data).build();
    }

    public static RpcResponse fail(){
        return RpcResponse.builder().code(500).data("服务器错误").build();
    }

    public static RpcResponse fail(int code, String message){
        return RpcResponse.builder().code(code).data(message).build();
    }
}
