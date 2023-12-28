package com.dmz.api.member.domain;

import java.util.List;

import org.hibernate.annotations.Comment;

import com.dmz.api.community.domain.Community;
import com.dmz.api.community.domain.Reply;
import com.dmz.global.entity.BaseTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.dmz.api.member.domain
 * fileName       : Member
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
@Comment("회원")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("닉네임")
	@Column(nullable = false)
	private String nickname;

	@Comment("소셜로그인 식별자")
	@Column(nullable = false, unique = true)
	private String providerId;

	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	private List<Community> communityList;

	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	private List<Reply> replyList;

	//TODO : 프로필이미지

	@Builder
	public Member(String nickname, String providerId) {
		this.nickname = nickname;
		this.providerId = providerId;
	}

}
