package com.example.ribbon.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    //配置返回的方法，就是当发生错误的时候能够有一个熔断机制
    @HystrixCommand(fallbackMethod = "hiFallBack")
    public String getUser(@RequestParam("id")String id){
        this.loadBalancerClient.choose("CLIENT1");
        return restTemplate.getForEntity("http://CLIENT1/user/findById?id="+id, String.class).getBody();
    }

    @RequestMapping("/hi")
    //配置返回的方法，就是当发生错误的时候能够有一个熔断机制
    @HystrixCommand(fallbackMethod = "hiFallBack")
    public String hi(@RequestParam("id")String id){
        return restTemplate.getForObject("http://CLIENT1/user/findById?id="+id,String.class);
    }
    //熔断后调用的方法
    public String hiFallBack(String id){
        return "hi远程调用超时"+id;
    }
}
