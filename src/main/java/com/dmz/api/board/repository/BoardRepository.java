package com.dmz.api.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmz.api.board.domain.Board;

/**
 * packageName    : com.idenit.api.board.repository
 * fileName       : BoardRepository
 * author         : Hyuk Kim
 * date           : 2023-09-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-19        Hyuk Kim       최초 생성
 */
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
