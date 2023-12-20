package com.idenit.api.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idenit.api.member.response.MemberResponse;
import com.idenit.global.security.MemberAuthorize;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.api.member
 * fileName       : MemberController
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@RestController
@MemberAuthorize
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

	private final MemberService memberService;

	@GetMapping
	public MemberResponse get() {

		return memberService.getMember();
	}

}
