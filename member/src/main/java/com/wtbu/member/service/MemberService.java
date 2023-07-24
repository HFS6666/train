package com.wtbu.member.service;

import com.wtbu.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Resource
    MemberMapper memberMapper;
    public int count(){
        return Math.toIntExact(memberMapper.countByExample(null)) ;
    }

}
