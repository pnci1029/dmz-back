package com.idenit.global.advice;

import org.springframework.http.HttpStatus;

import lombok.Builder;

/**
 * packageName    : com.idenit.global.advice
 * fileName       : ServerErrorResponse
 * author         : MinKyu Park
 * date           : 2023-10-13
 * description    : 
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-13        MinKyu Park       최초 생성
 */
public record ServerErrorResponse<T>(HttpStatus code, T message) {

	@Builder
	public ServerErrorResponse {
	}

}