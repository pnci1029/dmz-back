package com.dmz.api.community.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmz.api.community.dto.request.CommunitySearch;
import com.dmz.api.community.dto.response.CommunityResponse;
import com.dmz.api.community.service.CommunityService;
import com.dmz.global.utils.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.api.community.controller
 * fileName       : CommunityController
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
@Tag(name = "게시물 관리")
public class CommunityController {

	private final CommunityService communityService;

	@GetMapping
	@Operation(summary = "커뮤니티 조회", description = "메서드에 대한 설명")
	@ApiResponse(responseCode = "200", description = "", content = @Content(schema = @Schema(implementation = CommunityResponse.class)))
	public Response<?> getCommunityList(CommunitySearch search) {

		return communityService.getCommunityList(search);
	}

}
