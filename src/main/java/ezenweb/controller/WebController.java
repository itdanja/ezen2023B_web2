package ezenweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value =  {"/" , "/chatting"} )
    public String forward() {
        return "forward:/index.html";
    }
}