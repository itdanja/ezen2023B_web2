package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
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
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    // * 리포지토리 객체
    @Autowired private BoardEntityRepository boardEntityRepository;
    @Autowired private MemberEntityRepository memberEntityRepository;
    @Autowired private ReplyEntityRepository replyEntityRepository;
    @Autowired private MemberService memberService;

    // 1. C
    @Transactional
    public boolean postBoard( BoardDto boardDto){ //  ======= 테스트 ==========
        MemberDto loginDto =  memberService.doLoginInfo();
        if( loginDto == null ) return false;

        // 1. 로그인된 회원 엔티티 찾기
        Optional< MemberEntity > optionalMemberEntity =
            memberEntityRepository.findById( loginDto.getMno() );
        // 2. 찾은 엔티티가 존재하지 않으면 실패;
        if( !optionalMemberEntity.isPresent() ) return false;
        // 3. 엔티티 꺼내기
        MemberEntity memberEntity = optionalMemberEntity.get();
           // - 글쓰기
        BoardEntity saveBoard = boardEntityRepository.save( boardDto.toEntity() ) ;
            // - FK 대입
        if( saveBoard.getBno() >= 1){ // 글쓰기를 성공했으면
            saveBoard.setMemberEntity( memberEntity );
            return true;
        }
        return false;
    }
    // 2. R
    @Transactional
    public List<Object> getBoard(){
        // 1. 리포지토리를 이용한 모든 엔티티( 테이블에 매핑 하기전 엔티티 )를 호출
        List<BoardEntity> result = boardEntityRepository.findAll();
        System.out.println("result = " + result);
        System.out.println("작성자 : " + result.get(0).getMemberEntity().getMemail() );

        return null;
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
