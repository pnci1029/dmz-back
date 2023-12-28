package com.dmz.api.community.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dmz.api.community.dto.request.CommunitySearch;
import com.dmz.api.community.repository.CommunityPagingRepository;
import com.dmz.api.community.repository.ReplyRepository;
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
	private final ReplyRepository repository;

	public Response<?> getCommunityList(CommunitySearch search) {

		PageRequest pageable = search.getPageable(search);

		return Response.list(communityPagingRepository.selectCommunityList(search, pageable));
	}
}
