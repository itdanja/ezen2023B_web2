package ezenweb.controller;

import ezenweb.model.entity.BoardEntity;
import ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody( Content-Type:application/json )  : 데이터 주고 받는 REST 역할
@RequestMapping("/board")
@CrossOrigin("http://localhost:3000") // 요청 근원지를 교차 허용
public class BoardController {
    @Autowired private BoardService boardService;
    @PostMapping("/post.do")
    public boolean postBoard(){  return boardService.postBoard(); }
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









