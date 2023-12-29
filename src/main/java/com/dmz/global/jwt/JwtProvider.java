package com.dmz.global.jwt;

import static com.dmz.global.constants.JWT.*;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dmz.global.exception.UnAuthorized;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName    : com.idenit.global.jwt
 * fileName       : JwtProvider
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    : Jwt Provider
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Slf4j
@Component
public class JwtProvider {

	private final Key key;

	public JwtProvider() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	/**
	 * 토큰 생성 메서드
	 *
	 * @param uid PK
	 * @param role 역할
	 * @return jwt
	 */
	public Token generateToken(String uid, String role) {

		long now = new Date().getTime();
		Date tokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

		String accessToken = Jwts.builder()
			.setSubject(uid)
			.claim(AUTHORITIES_KEY, role)
			.setExpiration(tokenExpiresIn)
			.signWith(key, SignatureAlgorithm.HS512)
			.compact();

		return Token.builder()
			.accessToken(accessToken)
			.expiresIn(tokenExpiresIn.getTime())
			.build();
	}

	/**
	 * 계정 정보 추출 메서드
	 *
	 * @param accessToken 인증 토큰
	 * @return auth data
	 */
	public Authentication getAuthentication(String accessToken) {

		Claims claims = parseClaims(accessToken);

		if (claims.get(AUTHORITIES_KEY) == null) {
			throw new UnAuthorized();
		}

		Collection<? extends GrantedAuthority> authorities = Arrays.stream(
				claims.get(AUTHORITIES_KEY).toString().split(","))
			.map(SimpleGrantedAuthority::new)
			.toList();

		UserDetails principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, "", authorities);
	}

	/**
	 * 토큰 유효성 검사 메서드
	 *
	 * @param token 토큰
	 * @return true, false
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("잘못된 Jwt 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("만료된 Jwt 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("지원되지 않는 Jwt 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("Jwt 토큰이 잘못되었습니다.");
		}
		return false;
	}

	/**
	 * 클레임 파서 메서드
	 *
	 * @param accessToken 인증 토큰
	 * @return claims
	 */
	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
