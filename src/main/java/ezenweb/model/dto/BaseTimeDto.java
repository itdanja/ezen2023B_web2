package ezenweb.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class BaseTimeDto  {

    public String cdate;
    // 2.레코드/엔티티 수정날짜
    public String udate;

}
