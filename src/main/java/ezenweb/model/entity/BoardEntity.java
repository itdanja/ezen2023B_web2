package ezenweb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 해당 클래스와 연동DB내 테이블과 매핑/연결 ( ORM )
@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
public class BoardEntity { // 테이블
    @Id // PK
    private int bno;        // 게시물번호 PK
    private String btitle; // 게시물제목

}
/*
    create table BoardEntitiy(
        bno int ,
        btitle varchar(255)
    )
*/
