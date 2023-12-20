package com.idenit.api.member;

import org.springframework.stereotype.Service;

import com.idenit.api.member.domain.Member;
import com.idenit.api.member.exception.MemberNotFoundException;
import com.idenit.api.member.repository.MemberRepository;
import com.idenit.api.member.response.MemberResponse;
import com.idenit.global.jwt.Jwt;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.api.member
 * fileName       : MemberService
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	/**
	 * 회원 단일 조회
	 * @return MemberResponse
	 */
	public MemberResponse getMember() {

		String id = Jwt.getId();

		Member member = memberRepository.findById("id")
			.orElseThrow(MemberNotFoundException::new);

		return MemberResponse.toDto(member);
	}
}
