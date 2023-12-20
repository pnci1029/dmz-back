package com.idenit.global.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

/**
 * packageName    : com.idenit.global.entity
 * fileName       : GlobalException
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Getter
@MappedSuperclass
public class BaseTime {

	@Column(updatable = false)
	private LocalDateTime createdAt;

	@Column(updatable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
}
