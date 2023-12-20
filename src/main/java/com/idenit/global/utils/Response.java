package com.idenit.global.utils;

import static org.springframework.http.HttpStatus.*;

import java.util.Collections;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.idenit.global.utils.paging.Paging;

import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.idenit.global.utils
 * fileName       : Response
 * author         : Jihun Kim
 * date           : 10/10/23
 * description    : 응답 객체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/10/23        Jihun Kim       최초 생성
 */
@Getter
@Builder
public class Response<T> {

	private HttpStatus status;

	private T data;

	public static Response<?> ok() {

		return Response.builder()
			.status(OK)
			.data(Collections.EMPTY_LIST)
			.build();
	}

	public static <T> Response<?> ok(T data) {

		return Response.builder()
			.status(OK)
			.data(data)
			.build();
	}

	public static Response<?> list(Page<?> data) {

		return Response.builder()
			.status(OK)
			.data(Paging.of(data))
			.build();
	}

}
