package com.idenit.api.board.request;

import java.time.LocalDate;

import com.idenit.api.board.enums.BoardType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.idenit.api.board.request
 * fileName       : BoardFilterRequest
 * author         : Hyuk Kim
 * date           : 2023-09-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-19        Hyuk Kim       최초 생성
 */
@Getter
@NoArgsConstructor
public class BoardFilterRequest {

	private BoardType type;

	private String title;

	private String contents;

	private LocalDate startDate;

	private LocalDate endDate;

	@Builder
	public BoardFilterRequest(BoardType type, String title, String contents, LocalDate startDate, LocalDate endDate) {
		this.type = type;
		this.title = title;
		this.contents = contents;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
