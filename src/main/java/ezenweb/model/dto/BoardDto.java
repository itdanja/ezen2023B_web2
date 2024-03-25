package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
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
public class BoardDto {
    private int bno;

    private String bcontent;

    private int bview;

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bcontent( this.bcontent )
                .build();
    }

}
