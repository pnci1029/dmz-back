package com.dmz.api.community.dto.response;

import java.util.List;

import com.dmz.api.community.domain.TechStack;
import com.dmz.api.community.enums.CommunityType;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.dmz.api.community.dto.response
 * fileName       : CommunityResponse
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Getter
@Builder
public class CommunityResponse {

	@Schema(example = "1", description = "게시글의 식별자")
	private Long id;
	@Schema(example = "프로젝트 팀원 모집합니다", description = "게시글 제목")
	private String title;

	@Schema(example = "지속적으로 소통하며 함께 할 수 있는 팀원 모집합니다", description = "게시글 내용")
	private String content;

	@Schema(example = "STUDY", description = "STUDY:스터디, PROJECT:프로젝트, MENTORING:멘토링")
	private CommunityType type;

	@Schema(type = "array", example = "[\"JAVA\", \"REACT\"]", description = "기술스택 목록")
	private List<TechStack> techList;

	@QueryProjection
	public CommunityResponse(Long id, String title, String content, CommunityType type, List<TechStack> techList) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.type = type;
		this.techList = techList;
	}
}
