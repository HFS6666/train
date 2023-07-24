package com.wtbu.member.service;

import cn.hutool.core.collection.CollUtil;
import com.wtbu.member.domain.Member;
import com.wtbu.member.domain.MemberExample;
import com.wtbu.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Resource
    MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public Long register(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(list)) {
            throw  new RuntimeException("手机号已经注册");
        }
        Member m = new Member();
        m.setId(System.currentTimeMillis());
        m.setMobile(mobile);
        memberMapper.insert(m);
        return m.getId();
    }

}
