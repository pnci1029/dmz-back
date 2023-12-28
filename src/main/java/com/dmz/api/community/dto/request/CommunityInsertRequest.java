package com.dmz.api.community.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.dmz.api.community.enums.CommunityType;
import com.dmz.api.community.enums.Tech;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.dmz.api.community.dto.request
 * fileName       : CommunityInsertReqeust
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityInsertRequest {

	@Schema(example = "프로젝트 팀원 모집합니다", description = "게시글 제목")
	private String title;

	@Schema(example = "지속적으로 소통하며 함께 할 수 있는 팀원 모집합니다", description = "게시글 내용")
	private String content;

	@Schema(example = "STUDY", description = "STUDY:스터디, PROJECT:프로젝트, MENTORING:멘토링")
	private CommunityType type;

	@Schema(type = "array", example = "[\"JAVA\", \"REACT\"]", description = "기술스택 목록")
	private final List<Tech> techList = new ArrayList<>();

}
