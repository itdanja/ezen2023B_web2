package ezenweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactController {

    @GetMapping(value =  {"/" , "/board/write","/member/signup","/member/login","/board","/chat","/error403"} )
    public String forward() {
        return "forward:/index.html";
    }

}
