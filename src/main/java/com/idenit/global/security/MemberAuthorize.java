package com.idenit.global.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * packageName    : com.idenit.global.security
 * fileName       : MemberAuthorize
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    : 회원 권한 어노테이션
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('MEMBER')")
public @interface MemberAuthorize {
}
