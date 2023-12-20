package com.idenit.api.board.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.api.board.domain
 * fileName       : BoardType
 * author         : Hyuk Kim
 * date           : 2023-09-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-19        Hyuk Kim       최초 생성
 */
@Getter
@RequiredArgsConstructor
public enum BoardType {

	INQUIRY("문의사항");

	private final String value;
}
