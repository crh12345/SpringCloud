package com.example.feign.controller;

import com.example.feign.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/user/findById",method = RequestMethod.GET)
    public String findById(@RequestParam("id") String id) {
        return clientService.findById(id);
    }
}
