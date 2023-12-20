package com.dmz.global.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.idenit.global.jwt
 * fileName       : Jwt
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Getter
@NoArgsConstructor
public class Token {

	private String accessToken;

	private Long expiresIn;

	@Builder
	public Token(String accessToken, Long expiresIn) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}
}
