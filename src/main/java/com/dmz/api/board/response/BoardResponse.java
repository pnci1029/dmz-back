package com.dmz.api.board.response;

import java.time.LocalDateTime;

import com.dmz.api.board.enums.BoardType;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.idenit.api.board.response
 * fileName       : BoardResponse
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
public class BoardResponse {

	private Long id;

	private BoardType type;

	private String title;

	private String contents;

	private LocalDateTime createdAt;

	@Builder
	@QueryProjection
	public BoardResponse(Long id, BoardType type, String title, String contents, LocalDateTime createdAt) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.contents = contents;
		this.createdAt = createdAt;
	}

}
