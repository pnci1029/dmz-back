package com.dmz.api.community.domain;

import java.util.List;

import org.hibernate.annotations.Comment;

import com.dmz.api.community.enums.CommunityType;
import com.dmz.api.member.domain.Member;
import com.dmz.global.entity.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.dmz.api.content.domain
 * fileName       : Content
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Entity
@Getter
@Comment("게시물 - 프로젝트,스터디")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@OneToMany(mappedBy = "community")
	private List<TechStack> techStackList;

	@OneToMany(mappedBy = "community")
	private List<Reply> replyList;

	@Comment("제목")
	@Column(nullable = false, length = 30)
	private String title;

	@Comment("게시물의 타입")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CommunityType type;

	@Comment("내용")
	@Column(nullable = false, length = 500)
	private String content;

//	TODO : 모집마감일 , 시작일 , 마감일 , 조회수 , 진행방식(온,오프라인)

}
