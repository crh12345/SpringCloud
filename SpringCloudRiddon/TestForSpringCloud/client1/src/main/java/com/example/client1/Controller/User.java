package com.example.client1.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User {
    @RequestMapping("/user/findById")
    public String findById(@RequestParam("id")String id){
        return "这个是Spring客户端2为"+id;
    }
}
