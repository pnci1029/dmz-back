package com.idenit.api.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.api.member.domain
 * fileName       : MemberRole
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Getter
@RequiredArgsConstructor
public enum MemberRole {

	MEMBER("MEMBER");

	private final String value;
}
