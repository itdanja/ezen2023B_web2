package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MemberDto {
    private int mno;

    private String memail;

    private String mpassword;

    private String mname;

    private String mrol;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .mno( this.mno )
                .memail( this.memail )
                .mname( this.mname )
                .mrol( this.mrol )
                .mpassword( this.mpassword )
                .build();
    }

}
