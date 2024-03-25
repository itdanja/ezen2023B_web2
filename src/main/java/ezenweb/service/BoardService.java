package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    // * 리포지토리 객체
    @Autowired private BoardEntityRepository boardEntityRepository;
    @Autowired private MemberEntityRepository memberEntityRepository;
    @Autowired private ReplyEntityRepository replyEntityRepository;
    @Autowired private MemberService memberService;
    // 1. C
    @Transactional
    public boolean postBoard(BoardDto boardDto ){ //  ======= 테스트 ==========
        // 1. 회원가입
        int loginMno = memberService.getLoginMno();
        System.out.println("loginMno = " + loginMno);
        MemberEntity saveMemberEntity = memberEntityRepository.findById( loginMno  ).get();

        // 2. 회원가입된 회원으로 글쓰기
            // 1. 엔티티 객체 생성
        BoardEntity boardEntity = boardDto.toEntity();
            // 2.************  [FK대입]
        boardEntity.setMemberEntity( saveMemberEntity ); //회원 엔티티 대입 시 DB에서는 PK만 저장
            // 3. 해당 엔티티를 db에 저장할수 있도록 조작
        BoardEntity saveBoardEntity = boardEntityRepository.save( boardEntity );

        return true;
    }
    // 2. R
    @Transactional
    public List<BoardDto> getBoard(){
        // 1. 리포지토리를 이용한 모든 엔티티( 테이블에 매핑 하기전 엔티티 )를 호출
        List<BoardEntity> result = boardEntityRepository.findAll();
        return result.stream().map( ( data )->{ return data.toDto();}).collect(Collectors.toList());
    }
    // 3. U
    @Transactional
    public boolean putBoard(){
        BoardEntity boardEntity = boardEntityRepository.findById( 1 ).get();
        boardEntity.setBcontent("JPA수정테스트중");
        return false;
    }
    // 4. D
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById( 1 );
        return false;
    }
}
