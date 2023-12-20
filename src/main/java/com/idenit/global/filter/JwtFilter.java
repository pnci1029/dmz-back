package com.idenit.global.filter;

import static com.idenit.global.constants.JWT.*;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.idenit.global.jwt.JwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.global.filter
 * fileName       : JwtFilter
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		String jwt = resolveToken(request);

		if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
			Authentication authentication = jwtProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * 토큰 분리 메서드
	 *
	 * @param request request
	 * @return jwt
	 */
	private String resolveToken(HttpServletRequest request) {

		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(7);
		}

		return null;
	}

}
