package com.dmz.api.member.domain;

import com.dmz.api.auth.request.JoinRequest;
import com.dmz.api.member.enums.MemberRole;
import com.dmz.global.entity.BaseTime;
import com.dmz.global.utils.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.idenit.api.member.domain
 * fileName       : Member
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {

	@Id
	@Column(nullable = false)
	private String id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MemberRole role;

	@Builder
	public Member(String id, String password, String name, String phone, String email, MemberRole role) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.role = role;
	}

	/**
	 * 회원가입 전용
	 * @param request 회원가입 모델
	 * @return Member
	 */
	public static Member tojoinMember(JoinRequest request) {
		return Member.builder()
			.id(request.getId())
			.password(PasswordEncoder.encrypt(request.getPassword()))
			.name(request.getName())
			.phone(request.getPhone())
			.email(request.getEmail())
			.role(MemberRole.MEMBER)
			.build();
	}
}
