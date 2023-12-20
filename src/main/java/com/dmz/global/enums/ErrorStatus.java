package com.dmz.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.idenit.global.enums
 * fileName       : ErrorStatus
 * author         : Jihun Kim
 * date           : 10/10/23
 * description    : 
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/10/23        Jihun Kim       최초 생성
 */
@Getter
@AllArgsConstructor
public enum ErrorStatus {

	P01("파라미터 유효성 검사 실패"),
	T01("토큰 유효성 검증 실패"),
	A01("권한이 없음"),
	A02("계정을 찾을 수 없음"),
	D01("중복된 데이터"),
	R01("API 존재하지 않음"),
	R02("Content-type 불일치"),
	R03("미디어 타입 미지원");

	private final String description;

}
