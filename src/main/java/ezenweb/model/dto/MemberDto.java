package ezenweb.model.dto;

import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class MemberDto {
    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;

    // - dto를 엔티티로 변환하는 메소드 // C
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno( this.mno )
                .mname( this.mname )
                .memail( this.memail )
                .mpassword( this.mpassword )
                .mrol( this.mrol )
                .build();
        // this ?? : 해당 메소드를 호출한 인스턴스
    }

}
