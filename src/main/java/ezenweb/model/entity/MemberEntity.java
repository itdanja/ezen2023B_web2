package ezenweb.model.entity;

import ezenweb.model.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter@Builder@ToString@DynamicInsert@DynamicUpdate
public class MemberEntity extends BaseTime {
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
    @OneToMany( mappedBy = "memberEntity" )
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    // - 엔티티를 dto로 변환하는 메소드 // R
    public MemberDto toDto(){
        return MemberDto.builder()
                .mno( this.mno )
                .mname( this.mname )
                .memail( this.memail )
                .mpassword( this.mpassword )
                .mrol( this.mrol )
                .cdate( this.getCdate().toLocalDate().toString()  )
                .udate( this.getUdate().toLocalDate().toString() )
                .build();
    }
}

/*
        fetch : 양방향일때 참조를 불러오는 로딩 옵션
            fetch = FetchType.LAZY            : 참조를 사용할때 로딩 [ 지연 로딩  ]  자바에서 .get~~~ 할때 객체 참조해서 불러오고
            fetch = FetchType.EAGER [ 기본값 ] : 참조값을 즉시 로딩   [ 즉시 로딩 ]  db에서 select 할때 객체 참조해서 불러오고

        cascade : 영속성 제약조건 ( 엔티티 객체 기준 ) : 서로 연관된 객체들 끼리(부-자)의 영향을 끼치게 할껀지
            [REMOVE+PERSIST]   cascade = CascadeType.ALL       :
            [모두제거]          cascade =CascadeType.REMOVE     : 부모 가 삭제될때 자식도 같이 삭제 [ 부모와 자식 를 모두 제거 ]
            [영속성]            cascade =CascadeType.PERSIST    : 부모 호출 할때 자식도 하나로 인식 [ 부모와 자식 를 한번에 영속성 활성화 ]
                - 부모를 저장하면 자식도 같이 저장
            [병합]            cascade =CascadeType.MERGE      : 부모 가 수정될떄 자식도 조회후 업데이트
            [새로고침]         cascade =CascadeType.REFRESH    : 부모 가 업데이트 되면 자식 값 새로고침
            [영속성제거]       cascade =CascadeType.DETACH     : 영속성 제거

 */











