package com.idenit.api.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idenit.api.auth.request.JoinRequest;
import com.idenit.api.auth.request.LoginRequest;
import com.idenit.global.utils.Response;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.api.auth
 * fileName       : AuthController
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public void signup(@RequestBody @Valid JoinRequest req) {

		authService.signup(req);
	}

	@PostMapping("/login")
	public Response<?> login(@RequestBody @Valid LoginRequest req) {

		return Response.ok(authService.login(req));
	}

}
