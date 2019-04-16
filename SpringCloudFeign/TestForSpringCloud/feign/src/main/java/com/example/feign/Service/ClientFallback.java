package com.example.feign.Service;

import org.springframework.stereotype.Component;

@Component
public class ClientFallback implements ClientService{

    @Override
    public String findById(String id) {
        return "调用Client1服务超时，调用方法findById（id）-根据id查询所有记录"+id;
    }
}
