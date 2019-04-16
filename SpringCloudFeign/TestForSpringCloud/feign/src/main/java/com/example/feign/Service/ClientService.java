package com.example.feign.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "client1",fallback = ClientFallback.class)
public interface ClientService {
    @GetMapping(value = "/user/findById")
    String findById(@RequestParam("id") String id);
}
