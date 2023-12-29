package com.dmz.global.utils.paging;

import static org.springframework.data.domain.Sort.Direction.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.idenit.global.utils.paging
 * fileName       : Page
 * author         : MinKyu Park
 * date           : 2023-10-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-25        MinKyu Park       최초 생성
 */
@Getter
@Setter
public class Page {

	@Schema(description = "페이지 -> 0부터 시작합니다.", example = "0")
	private Integer page;

	@Schema(description = "사이즈 -> 페이지당 데이터 수", example = "10")
	private Integer size;

	// @Schema(example = "DESC", description = "DESC / ASC")
	@Schema(hidden = true)
	private Sort.Direction order = DESC;

	// @Schema(example = "createdAt", description = "정렬할 파라미터 명")
	@Schema(hidden = true)
	private String sort = "createdAt";

	public PageRequest getPageable(Page page) {
		return PageRequest.of(page.getPage(), page.getSize(), page.getOrder(), page.getSort());
	}
}
