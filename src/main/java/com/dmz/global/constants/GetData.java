package com.dmz.global.constants;

import org.springframework.stereotype.Component;

import com.dmz.api.member.domain.Member;
import com.dmz.api.member.exception.MemberNotFoundException;
import com.dmz.api.member.repository.MemberRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.global.constants
 * fileName       : GetData
 * author         : MinKyu Park
 * date           : 2023-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-30        MinKyu Park       최초 생성
 */
@Component
@RequiredArgsConstructor
public class GetData {
	private final MemberRepository memberRepository;

	public Member member(Long memberId) {
		return memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
	}
}
