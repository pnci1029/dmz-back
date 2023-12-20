package com.idenit.global.exception;

import java.util.HashMap;
import java.util.Map;

import com.idenit.global.enums.ErrorStatus;

import lombok.Getter;

/**
 * packageName    : com.idenit.global.exception
 * fileName       : GlobalException
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Getter
public abstract class GlobalException extends RuntimeException {
	private final Map<String, String> validation = new HashMap<>();

	protected GlobalException(String message) {
		super(message);
	}

	public abstract ErrorStatus code();
}