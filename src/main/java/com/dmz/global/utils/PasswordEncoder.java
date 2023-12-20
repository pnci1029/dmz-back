package com.dmz.global.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * packageName    : com.idenit.global.utils
 * fileName       : PasswordEncoder
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
public class PasswordEncoder {

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public static String encrypt(String rawPassword) {
		return encoder.encode(rawPassword);
	}

	public static boolean matches(String rawPassword, String encrpytedPassword) {
		return encoder.matches(rawPassword, encrpytedPassword);
	}
}
