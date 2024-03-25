package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class ReplyEntity {
    @Id // PK
    @GeneratedValue( strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private int rno;
    private String rcontent; // varchar(255)

    // 단방향 : FK 필드
    @JoinColumn( name = "bno_fk") // fk 필드명
    @ManyToOne // 해당 필드 참조
    private BoardEntity boardEntity;

    // 단방향 : FK 필드
    @JoinColumn( name = "mno_fk")
    @ManyToOne
    private MemberEntity memberEntity;

}
