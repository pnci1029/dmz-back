package com.dmz.global.advice;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.dmz.global.enums.ErrorStatus;

import lombok.Builder;

/**
 * packageName    : com.idenit.global.advice
 * fileName       : ErrorResponse
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
public record ErrorResponse(ErrorStatus code, String message, @JsonInclude(JsonInclude.Include.NON_EMPTY) Map<String, String> validation) {

	@Builder
	public ErrorResponse {
	}

}