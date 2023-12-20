package com.idenit.api.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.idenit.api.board.request.BoardFilterRequest;
import com.idenit.api.board.response.BoardResponse;

/**
 * packageName    : com.idenit.api.board.repository
 * fileName       : BoardRepositoryCustom
 * author         : Hyuk Kim
 * date           : 2023-09-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-19        Hyuk Kim       최초 생성
 */
public interface BoardRepositoryCustom {

	Page<BoardResponse> getList(BoardFilterRequest request, Pageable pageable);

}
