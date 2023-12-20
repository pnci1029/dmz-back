package com.idenit.global.jwt;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * packageName    : com.idenit.global.jwt
 * fileName       : JwtAccessDeniedHandler
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	/**
	 * 권한없이 접근 시 403
	 *
	 * @param request that resulted in an <code>AccessDeniedException</code>
	 * @param response so that the user agent can be advised of the failure
	 * @param accessDeniedException that caused the invocation
	 * @throws IOException IOException
	 * @throws ServletException ServletException
	 */
	@Override
	public void handle(
		HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
		throws IOException, ServletException {

		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

}
