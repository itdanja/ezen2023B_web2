package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody( Content-Type:application/json )  : 데이터 주고 받는 REST 역할
@RequestMapping("/board")
public class BoardController {
    @Autowired private BoardService boardService;

    @PostMapping("/post.do")
    public boolean postBoard( BoardDto boardDto ){
        return boardService.postBoard( boardDto );
    }

    @GetMapping("/get.do")
    public List<Object> getBoard(){
        return boardService.getBoard();
    }
    @PutMapping("/put.do")
    public boolean putBoard(){
        return boardService.putBoard();
    }
    @DeleteMapping("/delete.do")
    public boolean deleteBoard(){
        return boardService.deleteBoard();
    }

}









