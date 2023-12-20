package com.dmz.api.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmz.api.board.domain.Board;
import com.dmz.api.board.repository.BoardRepository;
import com.dmz.api.board.request.BoardFilterRequest;
import com.dmz.api.board.request.CreateBoardRequest;
import com.dmz.api.board.response.BoardResponse;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.api.board
 * fileName       : BoardService
 * author         : Hyuk Kim
 * date           : 2023-09-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-19        Hyuk Kim       최초 생성
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

	private final BoardRepository boardRepository;

	public Page<BoardResponse> boardList(BoardFilterRequest request, Pageable pageable) {
		return boardRepository.getList(request, pageable);
	}

	@Transactional
	public void createBoard(CreateBoardRequest request) {

		Board board = Board.toEntity(request);

		boardRepository.save(board);
	}

}
