package com.example.ribbon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @RequestMapping("")
    public String getUser(@RequestParam("id")String id){
        this.loadBalancerClient.choose("CLIENT1");
        return restTemplate.getForEntity("http://CLIENT1/user/findById?id="+id, String.class).getBody();
    }
    @RequestMapping("/hi")
    public String hi(@RequestParam("id")String id){
        return restTemplate.getForObject("http://CLIENT1/user/findById?id="+id,String.class);
    }
}
