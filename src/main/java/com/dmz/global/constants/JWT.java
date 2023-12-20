package com.dmz.global.constants;

import java.time.Duration;

/**
 * packageName    : com.idenit.global.constants
 * fileName       : JWT
 * author         : Jihun Kim
 * date           : 10/10/23
 * description    : 
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/10/23        Jihun Kim       최초 생성
 */
public class JWT {

	public static final String AUTHORITIES_KEY = "auth";

	public static final String BEARER_PREFIX = "Bearer ";

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static final long ACCESS_TOKEN_EXPIRE_TIME = Duration.ofDays(14).toMillis();

	public static final String SECRET_KEY = "BBF90F1D9986ACDF27284070F421A883036E9F5491B2E44BB75DA136E689372FC8297624F148467010509BF7EA0A8E56E2B9CE8E73DCF638A7FCE6ED73A8D807";

}
