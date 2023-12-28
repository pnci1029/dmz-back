package com.dmz.api.community.domain;

import org.hibernate.annotations.Comment;

import com.dmz.api.community.enums.Tech;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.dmz.api.community.domain
 * fileName       : TechStack
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
@Comment("기술스택")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TechStack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Community community;

	@Comment("기술스택")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Tech tech;

	@Builder
	public TechStack(Tech tech) {
		this.tech = tech;
	}
}
