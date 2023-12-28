package com.dmz.api.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmz.api.community.domain.Community;

/**
 * packageName    : com.dmz.api.community.repository
 * fileName       : CommunityRepository
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
public interface CommunityRepository extends JpaRepository<Community, Long> {
}
