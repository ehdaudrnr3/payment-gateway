package com.ssg.homework.t2021hw.service;

import com.ssg.homework.t2021hw.entity.Member;
import com.ssg.homework.t2021hw.exception.PaymentUnauthorizedException;
import com.ssg.homework.t2021hw.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 등록된 멤버 조회
     * @param mbrId
     * @return
     */
    public Member findMember(String mbrId) {
        return memberRepository.findById(mbrId)
                .orElseThrow(() -> new PaymentUnauthorizedException("invalid member id"));
    }

}
