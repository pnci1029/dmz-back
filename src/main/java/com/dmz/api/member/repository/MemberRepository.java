package com.dmz.api.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmz.api.member.domain.Member;

/**
 * packageName    : com.idenit.api.member.repository
 * fileName       : MemberRepository
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
public interface MemberRepository extends JpaRepository<Member, String> {
}
