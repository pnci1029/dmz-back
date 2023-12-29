package com.dmz.api.community.repository;

import static com.dmz.api.community.domain.QCommunity.*;
import static com.dmz.api.community.domain.QTechStack.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dmz.api.community.domain.QTechStack;
import com.dmz.api.community.dto.request.CommunitySearch;
import com.dmz.api.community.dto.response.CommunityResponse;
import com.dmz.api.community.dto.response.QCommunityResponse;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.api.community.repository
 * fileName       : CommunityPaging
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Repository
@RequiredArgsConstructor
public class CommunityPagingRepository {
	private final JPAQueryFactory queryFactory;

	public Page<CommunityResponse> selectCommunityList(CommunitySearch search, Pageable pageable) {
		List<CommunityResponse> content = queryFactory.select(new QCommunityResponse(
				community.id,
				community.title,
				community.content,
				community.type
			)).from(community)
			// .join(community.techStackList, techStack)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long count = queryFactory.select(community.count())
			.from(community)
			// .join(community.techStackList, techStack)
			.fetchOne();

		return new PageImpl<>(content, pageable, count != null ? count : 0L);
	}
}
