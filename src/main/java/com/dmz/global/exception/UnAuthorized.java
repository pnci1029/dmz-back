package com.dmz.global.exception;

import static com.dmz.global.enums.ErrorStatus.*;

import com.dmz.global.enums.ErrorStatus;

/**
 * packageName    : com.idenit.global.exception
 * fileName       : UnAuthorized
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
public class UnAuthorized extends GlobalException {

	private static final String MESSAGE = "인증이 필요합니다.";

	public UnAuthorized() {
		super(MESSAGE);
	}

	@Override
	public ErrorStatus code() {
		return A01;
	}
}
