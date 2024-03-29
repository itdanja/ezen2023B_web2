package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @SuperBuilder @ToString
public class MemberDto extends BaseTimeDto  {
    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;
    // - dto를 엔티티로 변환하는 메소드 // C
    public MemberEntity toEntity(){
        return MemberEntity.builder()    .mname( this.mname )
                .memail( this.memail ) .mpassword( this.mpassword )
                .build();
        // this ?? : 해당 메소드를 호출한 인스턴스
    }

}
