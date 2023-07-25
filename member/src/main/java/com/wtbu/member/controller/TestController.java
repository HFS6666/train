package com.wtbu.member.controller;

import com.wtbu.member.req.MemberRegisterReq;
import com.wtbu.member.req.MemberSendcode;
import com.wtbu.member.service.MemberService;
import com.wtbu.train.common.resp.CommonResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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
    public CommonResp<Integer> count(){
        return memberService.count();
    }
    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req){
        return memberService.register(req);
    }
    @PostMapping("/send-code")
    public CommonResp<Long> sendcode(@Valid MemberSendcode req){
         memberService.sendcode(req);
         return new CommonResp<>();
    }
}
