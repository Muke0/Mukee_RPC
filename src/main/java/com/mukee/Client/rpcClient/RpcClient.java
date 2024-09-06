package com.mukee.Client.rpcClient;

import com.mukee.common.Message.RpcRequest;
import com.mukee.common.Message.RpcResponse;

public interface RpcClient {
    RpcResponse sendRequest(RpcRequest request);
}
