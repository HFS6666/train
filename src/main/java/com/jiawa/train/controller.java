package com.jiawa.train;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping("/hello1")
    public Object say(){
        return "hello";
    }
}
