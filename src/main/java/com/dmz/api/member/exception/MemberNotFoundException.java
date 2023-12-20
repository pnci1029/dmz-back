package com.dmz.api.member.exception;

import static com.dmz.global.enums.ErrorStatus.*;

import com.dmz.global.enums.ErrorStatus;
import com.dmz.global.exception.GlobalException;

/**
 * packageName    : com.idenit.api.member.exception
 * fileName       : MemberNotFoundException
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
public class MemberNotFoundException extends GlobalException {

	private static final String MESSAGE = "인증이 필요합니다.";

	public MemberNotFoundException() {
		super(MESSAGE);
	}

	@Override
	public ErrorStatus code() {
		return D01;
	}
}

