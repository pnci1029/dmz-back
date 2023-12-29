package com.dmz.api.community.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmz.api.community.domain.Community;
import com.dmz.api.community.domain.TechStack;
import com.dmz.api.community.dto.request.CommunityInsertRequest;
import com.dmz.api.community.dto.request.CommunitySearch;
import com.dmz.api.community.enums.Tech;
import com.dmz.api.community.repository.CommunityPagingRepository;
import com.dmz.api.community.repository.CommunityRepository;
import com.dmz.api.community.repository.ReplyRepository;
import com.dmz.api.community.repository.TechStackRepository;
import com.dmz.api.member.domain.Member;
import com.dmz.global.constants.GetData;
import com.dmz.global.utils.Response;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.api.community.service
 * fileName       : CommunityService
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Service
@RequiredArgsConstructor
public class CommunityService {
	private final CommunityPagingRepository communityPagingRepository;
	private final CommunityRepository communityRepository;
	private final TechStackRepository techStackRepository;
	private final GetData getData;

	@Transactional(readOnly = true)
	public Response<?> getCommunityList(CommunitySearch search) {

		PageRequest pageable = search.getPageable(search);

		return Response.list(communityPagingRepository.selectCommunityList(search, pageable));
	}

	@Transactional
	public Response<?> addCommunity(CommunityInsertRequest request, Long loginMemberId) {

		Member member = getData.member(loginMemberId);

		Community community = CommunityInsertRequest.of(request, member);

		List<TechStack> techStacks = request.getTechList().stream()
			.map(t -> getTechStack(community, t)).toList();

		communityRepository.save(community);

		techStackRepository.saveAll(techStacks);

		return Response.ok();
	}

	private static TechStack getTechStack(Community community, Tech t) {
		return TechStack.builder().tech(t).community(community).build();
	}
}
