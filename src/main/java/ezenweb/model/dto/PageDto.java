package ezenweb.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @SuperBuilder @ToString
public class PageDto {

    private int page;               // 현재 페이지
    private int count;              // 총 페이지수
    private List<Object> data ;   // 본문 내용들

}
