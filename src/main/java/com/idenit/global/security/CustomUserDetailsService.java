package com.idenit.global.security;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.idenit.api.member.domain.Member;
import com.idenit.api.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.global.security
 * fileName       : CustomUserDetailsService
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    : 로그인 UserDetailsService
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	/**
	 * 회원 로그인
	 *
	 * @param username the username identifying the user whose data is required.
	 * @return 회원정보
	 * @throws UsernameNotFoundException UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Member member = memberRepository.findById(username)
			.orElseThrow(() -> new UsernameNotFoundException("로그인 실패"));

		return createUserDetails(member);
	}

	/**
	 * 회원 정보 생성
	 *
	 * @param member 로그인 응답 모델
	 * @return 회원 정보
	 */
	private UserDetails createUserDetails(Member member) {

		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + member.getRole().getValue());

		return new User(
			String.valueOf(member.getId()),
			member.getPassword(),
			Collections.singleton(grantedAuthority)
		);
	}

}
