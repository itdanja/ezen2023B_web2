package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter@Builder@ToString
public class MemberEntity {
    @Id // PK
    @GeneratedValue( strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private int mno;

    @Column( length = 50 , unique = true )      // varchar(50) unique
    private String memail;

    @Column( length = 30 )                      // varchar(30)
    private String mpassword;

    @Column( length = 20   , nullable = false) // varchar(20) , not null
    private String mname;

    @Column( name = "mrol"  ) // varchar(255) , not null
    @ColumnDefault( "'user'") // 문자 '' , 숫자 // ??????
    private String mrol;

    // 양방향 : 게시물fk      @OneToMany( mappedBy = "해당테이블 fk자바필드명")
    @OneToMany( mappedBy = "memberEntity") // 자바에서만 양방향
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default // 빌더패턴 사용해서 객체생성시 해당 필드의 초기값을 빌더 초기값으로 사용.
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    // 양방향 : 댓글fk
    @OneToMany( mappedBy = "memberEntity")
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();


}











