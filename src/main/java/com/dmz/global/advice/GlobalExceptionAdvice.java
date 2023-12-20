package com.dmz.global.advice;

import static com.dmz.global.enums.ErrorStatus.*;
import static org.springframework.http.HttpStatus.*;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dmz.global.enums.ErrorStatus;
import com.dmz.global.exception.GlobalException;

/**
 * packageName    : com.idenit.global.advice
 * fileName       : GlobalExceptionAdvice
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

	/**
	 * 예외 메시지 핸들러
	 *
	 * @param e Exception
	 * @return ErrorResponse
	 */
	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<ErrorResponse> errorException(GlobalException e) {

		ErrorResponse body = ErrorResponse.builder()
			.code(e.code())
			.message(e.getMessage())
			.validation(e.getValidation())
			.build();

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(body);
	}

	/**
	 * 권한 없음
	 *
	 * @return ErrorResponse
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> permissionException() {

		return ResponseEntity
			.status(BAD_REQUEST)
			.body(createBody(A01));
	}

	/**
	 * 계정 없음
	 *
	 * @return ErrorResponse
	 */
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponse> memberNotFoundException() {

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(createBody(A02));
	}

	/**
	 * 파라미터 오류
	 *
	 * @param e Exception
	 * @return ErrorResponse
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> parameterException(MethodArgumentNotValidException e) {

		Map<String, String> map = e.getFieldErrors().stream()
			.collect(Collectors.toMap(
				FieldError::getField,
				FieldError::getDefaultMessage
			));

		ErrorResponse body = createBody(P01, map);

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(body);
	}

	/**
	 * 메서드 불일치
	 *
	 * @return ErrorResponse
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> methodNotAllowedException() {

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(createBody(R01));
	}

	/**
	 *  컨텐트 타입 불일치
	 *
	 * @return ErrorResponse
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> contentTypeMismatchException() {

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(createBody(R02));
	}

	/**
	 * 미디어 타입 불일치
	 *
	 * @return ErrorResponse
	 */
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorResponse> mediaTypeMisMatchException() {

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(createBody(R03));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ServerErrorResponse<?>> serverException(RuntimeException e) {

		ServerErrorResponse<?> body = ServerErrorResponse.builder()
			.code(INTERNAL_SERVER_ERROR)
			.message(getErrorLog(e))
			.build();

		return ResponseEntity.status(BAD_REQUEST).body(body);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ServerErrorResponse<?>> serverException(Exception e) {

		ServerErrorResponse<?> body = ServerErrorResponse.builder()
			.code(INTERNAL_SERVER_ERROR)
			.message(getErrorLog(e))
			.build();

		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(body);
	}

	private ErrorResponse createBody(ErrorStatus e) {

		return ErrorResponse.builder()
			.code(e)
			.message(e.getDescription())
			.build();
	}

	private ErrorResponse createBody(ErrorStatus e, Map<String, String> map) {

		return ErrorResponse.builder()
			.code(e)
			.message(e.getDescription())
			.validation(map)
			.build();
	}

	private static String getErrorLog(Exception e) {

		return e.getMessage().isEmpty() ? e.getCause().getMessage() : e.getMessage();
	}

}
