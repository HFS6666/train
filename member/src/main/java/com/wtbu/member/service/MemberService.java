package com.wtbu.member.service;

import cn.hutool.core.collection.CollUtil;
import com.wtbu.member.domain.Member;
import com.wtbu.member.domain.MemberExample;
import com.wtbu.member.mapper.MemberMapper;
import com.wtbu.member.req.MemberRegisterReq;
import com.wtbu.train.common.resp.CommonResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Resource
    MemberMapper memberMapper;

    public CommonResp<Integer> count() {
        CommonResp<Integer> commonResp=new CommonResp<>();
        commonResp.setContent(Math.toIntExact(memberMapper.countByExample(null)));
        return commonResp;
    }

    public CommonResp<Long> register(MemberRegisterReq memberRegisterReq) {
        MemberExample memberExample = new MemberExample();
        String mobile=memberRegisterReq.getMobile();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(list)) {
            throw  new RuntimeException("手机号已经注册");
        }
        Member m = new Member();
        m.setId(System.currentTimeMillis());
        m.setMobile(mobile);
        memberMapper.insert(m);
        CommonResp<Long> commonResp=new CommonResp<>();
        commonResp.setContent(m.getId());
        return commonResp;
    }

}
