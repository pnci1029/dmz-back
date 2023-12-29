package com.dmz.api.auth.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * packageName    : com.idenit.api.auth.request
 * fileName       : LoginRequest
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Getter
public class LoginRequest {

	@NotBlank(message = "id: 아이디는 필수 값입니다.")
	@Schema(example = "dmz@dmz.com", description = "")
	private String id;
	@Schema(example = "1234", description = "")
	private String password ;

	public UsernamePasswordAuthenticationToken toAuthentication() {
		return new UsernamePasswordAuthenticationToken(id, password);
	}

}
