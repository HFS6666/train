package com.wtbu.member.controller;

import com.wtbu.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class TestController {
    @Resource
    MemberService memberService;
    @GetMapping("/count")
    public Integer count(){
        return memberService.count();
    }
    @PostMapping("/register")
    public Long register(String mobile){
        return memberService.register(mobile);
    }
}
