package ezenweb.example;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor@NoArgsConstructor
@Builder@Getter@Setter@ToString
public class 회원 {
    private int 번호;
    private String 아이디;
    private String 이름;
    @ToString.Exclude   @Builder.Default
    private List<게시물> 내가쓴글 = new ArrayList<>();

    @ToString.Exclude   @Builder.Default
    private List<댓글> 내가쓴댓글 = new ArrayList<>();

}
