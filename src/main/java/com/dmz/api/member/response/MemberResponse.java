package com.dmz.api.member.response;

import com.dmz.api.member.domain.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * packageName    : com.idenit.api.member.response
 * fileName       : MemberResponse
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Getter
@ToString
@NoArgsConstructor
public class MemberResponse {

	private String id;

	private String name;

	private String phone;

	private String email;

	private String role;

	@Builder
	public MemberResponse(String id, String name, String phone, String email, String role) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.role = role;
	}

	public static MemberResponse toDto(Member member) {
		return MemberResponse.builder()
			.id(member.getId())
			.name(member.getName())
			.phone(member.getPhone())
			.email(member.getEmail())
			.role(member.getRole().getValue())
			.build();
	}
}
