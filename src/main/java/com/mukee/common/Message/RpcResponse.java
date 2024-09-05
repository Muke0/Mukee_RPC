package com.mukee.common.Message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RpcResponse implements Serializable {
    private int code;
    private String message;
    private Object data;

    public static RpcResponse ok(Object data){
        return RpcResponse.builder().code(200).data(data).build();
    }

    public static RpcResponse fail(int code, String message){
        return RpcResponse.builder().code(code).data(message).build();
    }
}
