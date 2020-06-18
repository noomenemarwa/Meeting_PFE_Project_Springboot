package com.leoni.pfe.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello")
public class HelloController {

    @GetMapping(path = "/sayHi")
    public String sayHelloToMarwa() {
        return "Hi Marwa!";
    }

}
