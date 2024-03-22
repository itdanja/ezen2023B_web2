package ezenweb.example;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Builder @Getter @Setter @ToString
public class 게시물 {
    private int 번호;
    private String 제목;
    private String 내용;
    // 작성자 회원객체
    private 회원 작성자;
    private List<댓글> 댓글목록 = new ArrayList<>();
}
