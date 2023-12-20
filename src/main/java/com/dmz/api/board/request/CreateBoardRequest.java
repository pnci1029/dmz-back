package com.dmz.api.board.request;

import com.dmz.api.board.enums.BoardType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.idenit.api.board.request
 * fileName       : CreateBoardRequest
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
public class CreateBoardRequest {

	@NotNull(message = "글 타입은 필수 입니다.")
	private BoardType type;

	@NotBlank(message = "글 제목은 필수 입니다.")
	private String title;

	@NotBlank(message = "글 내용은 필수 입니다.")
	private String contents;

}
