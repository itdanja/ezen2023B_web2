package ezenweb.controller;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000") // 요청 근원지를 교차 허용
public class MemberController {

    @Autowired private MemberService memberService;

    @PostMapping("/signup/post.do")
    public boolean doSignupPost( @RequestBody MemberDto memberDto){
        return memberService.doSignupPost( memberDto );
    }

    @PostMapping("/login/post.do")
    public boolean doLoginPost(  MemberDto memberDto){
        return memberService.doLoginPost( memberDto );
    }
    @GetMapping("/login/info.do")
    public MemberDto doLoginInfo(  ){
        return memberService.doLoginInfo(  );
    }
    @GetMapping("/logout.do")
    public boolean doLogout(  ){
        return memberService.doLogout(  );
    }


}
