package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boardimg")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter @ToString
@Builder
public class BoardImgEntity extends BaseTime {
    @Id
    private String uuidfilename; // 이미지식별이름[PK]
    // FK 만들기
    @JoinColumn(name = "bno_fk") @ManyToOne
    private BoardEntity boardEntity;
}
