package com.idenit.global.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * packageName    : com.idenit.global.jwt
 * fileName       : JwtAuthenticationEntryPoint
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final HandlerExceptionResolver resolver;

	public JwtAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
		this.resolver = resolver;
	}

	/**
	 * 자격 증명 실패 401
	 * @param request that resulted in an <code>AuthenticationException</code>
	 * @param response so that the user agent can begin authentication
	 * @param authException that caused the invocation
	 * @throws IOException IOException
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) {

		resolver.resolveException(request, response, null, authException);
	}
}
