package ezenweb.model.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PageDto {

    // 1. 반환된 총 게시물들
    List<Map<Object , Object>> boardDtos;
    // 2. 반환된 총 페이지수
    int totalPages;
    // 3. 반환된 총 게시물수
    long totalCount;

}