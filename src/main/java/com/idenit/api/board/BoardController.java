package com.idenit.api.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idenit.api.board.request.BoardFilterRequest;
import com.idenit.api.board.request.CreateBoardRequest;
import com.idenit.api.board.response.BoardResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.api.board
 * fileName       : BoardController
 * author         : Hyuk Kim
 * date           : 2023-09-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-19        Hyuk Kim       최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

	private final BoardService boardService;

	@GetMapping
	public Page<BoardResponse> boardList(BoardFilterRequest request, Pageable pageable) {
		return boardService.boardList(request, pageable);
	}

	@PostMapping
	public void createBoard(@Valid @RequestBody CreateBoardRequest request) {
		boardService.createBoard(request);
	}

}
