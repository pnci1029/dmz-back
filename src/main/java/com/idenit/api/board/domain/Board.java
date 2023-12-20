package com.idenit.api.board.domain;

import com.idenit.api.board.enums.BoardType;
import com.idenit.api.board.request.CreateBoardRequest;
import com.idenit.global.entity.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.idenit.api.board.domain
 * fileName       : Board
 * author         : Hyuk Kim
 * date           : 2023-09-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-19        Hyuk Kim       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private BoardType type;

	@Column(nullable = false)
	private String title;

	@Lob
	@Column(nullable = false)
	private String contents;

	@Builder
	public Board(BoardType type, String title, String contents) {
		this.type = type;
		this.title = title;
		this.contents = contents;
	}

	public static Board toEntity(CreateBoardRequest request) {
		return Board.builder()
			.title(request.getTitle())
			.contents(request.getContents())
			.type(request.getType())
			.build();
	}
}
