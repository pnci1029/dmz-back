package com.dmz.api.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmz.api.community.domain.Reply;

/**
 * packageName    : com.dmz.api.community.repository
 * fileName       : ReplyRepository
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
