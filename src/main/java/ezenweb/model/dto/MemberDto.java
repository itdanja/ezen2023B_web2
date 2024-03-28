package ezenweb.model.dto;

import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @SuperBuilder @ToString
public class MemberDto extends BaseTimeDto {
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
/*

위 예시처럼 부모 클래스의 필드는 기존의 @Builder 어노테이션으로 설정을 할 수 없기 때문에
 stackoverflow를 검색하던 와중 @SuperBuilder 어노테이션을 발견했습니다.
 답변에 의하자면 lombok 1.18.2 버전부터 @SuperBuilder라는 어노테이션을 사용할 수 있고
  해당 어노테이션은 딱 제가 원하는대로 작동했습니다.
  위 Parent.class 와 Child.class에서 @Builder를 @SuperBuilder로 바꿔준다면
  실제 Output 과 동일한 결과가 나옵니다!

 */