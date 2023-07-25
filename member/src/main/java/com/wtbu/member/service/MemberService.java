package com.wtbu.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.wtbu.member.domain.Member;
import com.wtbu.member.domain.MemberExample;
import com.wtbu.member.mapper.MemberMapper;
import com.wtbu.member.req.MemberRegisterReq;
import com.wtbu.member.req.MemberSendcode;
import com.wtbu.train.common.exception.BusinessException;
import com.wtbu.train.common.exception.BusinessExceptionEnum;
import com.wtbu.train.common.resp.CommonResp;
import com.wtbu.train.common.util.SnowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);
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
            throw  new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member m = new Member();
        m.setId(SnowUtil.getSnowflakeNextId());
        m.setMobile(mobile);
        memberMapper.insert(m);
        CommonResp<Long> commonResp=new CommonResp<>();
        commonResp.setContent(m.getId());
        return commonResp;
    }
    public void sendcode(MemberSendcode memberSendcode) {
        MemberExample memberExample = new MemberExample();
        String mobile=memberSendcode.getMobile();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(list)) {
            LOG.info("手机号不存在，插入一条记录");
            Member m = new Member();
            m.setId(SnowUtil.getSnowflakeNextId());
            m.setMobile(mobile);
            memberMapper.insert(m);
        }
        String code=RandomUtil.randomString(4);
        LOG.info("生成短信验证码:{}",code);
    }

}
