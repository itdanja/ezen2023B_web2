package ezenweb.model.entity;

import ezenweb.model.entity.BoardEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boardimg")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class BoardImgEntity extends BaseTime {

    @Id
    private String bimg;    // 파일명( 중복 없다 -- 유저는 다수 고 서버는 하나) // 식별이 가능

    // 단방향 설정
    @JoinColumn( name="bno_fk") // 카멜표기법 사용하면 _ 들어감. // SQL은 대소문자 구분 안한다.
    @ManyToOne
    private BoardEntity boardEntity;

}