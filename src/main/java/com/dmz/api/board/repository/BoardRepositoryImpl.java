package com.dmz.api.board.repository;

import static com.dmz.api.board.domain.QBoard.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.dmz.api.board.enums.BoardType;
import com.dmz.api.board.request.BoardFilterRequest;
import com.dmz.api.board.response.BoardResponse;
import com.dmz.api.board.response.QBoardResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.api.board.repository
 * fileName       : BoardRepositoryImpl
 * author         : Hyuk Kim
 * date           : 2023-09-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-19        Hyuk Kim       최초 생성
 */
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<BoardResponse> getList(BoardFilterRequest request, Pageable pageable) {
		JPAQuery<Long> count = getTotal(request);
		List<BoardResponse> content = getSearchList(request, pageable);

		return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
	}

	/**
	 * 게시판 카운트 쿼리
	 * @param request BoardFilterRequest
	 * @return JPAQuery<Long>
	 */
	private JPAQuery<Long> getTotal(BoardFilterRequest request) {
		return queryFactory
			.select(board.count())
			.from(board)
			.where(typeEq(request.getType()),
				titleEq(request.getTitle()));
	}

	/**
	 * 게시판 페이징
	 * @param request - BoardFilterRequest
	 * @param pageable - Pageable
	 * @return - List<BoardResponse>
	 */
	private List<BoardResponse> getSearchList(BoardFilterRequest request, Pageable pageable) {
		return queryFactory.select(new QBoardResponse(
				board.id,
				board.type,
				board.title,
				board.contents,
				board.createdAt
			))
			.from(board)
			.where(titleEq(request.getTitle()),
				typeEq(request.getType()))
			.orderBy(board.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();
	}

	/**
	 * 제목 검색 조건
	 * @param title 제목
	 * @return boolean
	 */
	private BooleanExpression titleEq(String title) {

		if (!StringUtils.hasText(title)) {
			return null;
		}

		return board.title.contains(title);
	}

	/**
	 * 게시판 타입 검색 조건
	 * @param type 게시판 타입
	 * @return boolean
	 */
	private BooleanExpression typeEq(BoardType type) {

		return type != null ? board.type.eq(type) : null;
	}
}
