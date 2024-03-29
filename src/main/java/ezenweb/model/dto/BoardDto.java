package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @SuperBuilder @ToString
public class BoardDto extends BaseTimeDto {
    private int bno;
    private String bcontent;
    private int bview;
    private int mno_fk;     // (memberEntity) 회원 번호
    private String memail; //  (memberEntity) 회원 이메일
    // - 글쓰기
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bcontent( this.bcontent )
                .build();
    }
}
