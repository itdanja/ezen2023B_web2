package ezenweb.model.dto;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
public class BaseTimeDto {
    public LocalDateTime cdate;
    public LocalDateTime udate;
}
