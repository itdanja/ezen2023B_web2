package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.service.BoardService;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody( Content-Type:application/json )  : 데이터 주고 받는 REST 역할
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000")
public class MemberController {
    @Autowired private MemberService memberService;

    @PostMapping("/signup/post.do")
    public boolean postBoard( MemberDto memberDto ){
        return memberService.postBoard(memberDto);
    }

    @PostMapping("/login/post.do")
    public boolean getBoard( MemberDto memberDto ){
        return memberService.getBoard( memberDto );
    }

}









