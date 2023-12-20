package com.dmz.api.auth;

import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmz.api.auth.request.JoinRequest;
import com.dmz.api.auth.request.LoginRequest;
import com.dmz.api.member.domain.Member;
import com.dmz.api.member.repository.MemberRepository;
import com.dmz.global.jwt.JwtProvider;
import com.dmz.global.jwt.Token;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.api.auth
 * fileName       : AuthService
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
public class AuthService {

	private final JwtProvider jwtProvider;

	private final MemberRepository memberRepository;

	private final AuthenticationManagerBuilder managerBuilder;

	/**
	 * 회원가입 서비스
	 * @param joinRequest 회원가입 모델
	 */
	@Transactional
	public void signup(JoinRequest joinRequest) {

		Member member = Member.tojoinMember(joinRequest);

		memberRepository.save(member);
	}

	/**
	 * 로그인 서버스
	 * @param loginRequest 로그인 모델
	 * @return token
	 */
	@Transactional(readOnly = true)
	public Token login(LoginRequest loginRequest) {

		UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();

		Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

		String authorities = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));

		return jwtProvider.generateToken(authentication.getName(), authorities);
	}
}
