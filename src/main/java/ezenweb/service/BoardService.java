package ezenweb.service;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.repository.BoardEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class BoardService {
    // * 리포지토리 객체
    @Autowired private BoardEntityRepository boardEntityRepository;
    // 1. C
    @Transactional
    public boolean postBoard(){
        // 1. 엔티티( 테이블에 매핑 하기전 엔티티 ) 객체 생성
        BoardEntity boardEntity = BoardEntity.builder()
                .bno( 1 ).btitle("JPA작성테스트중").build();
        // 2. 리포지토리를 이용한 엔티티 를 테이블에 대입
        boardEntityRepository.save( boardEntity ); // insert
        return false;
    }
    // 2. R
    @Transactional
    public List<Object> getBoard(){
        // 1. 리포지토리를 이용한 모든 엔티티( 테이블에 매핑 하기전 엔티티 )를 호출
        List<BoardEntity> result = boardEntityRepository.findAll();
        System.out.println("result = " + result);
        return null;
    }
    // 3. U
    @Transactional
    public boolean putBoard(){
        BoardEntity boardEntity = boardEntityRepository.findById( 1 ).get();
        boardEntity.setBtitle("JPA수정테스트중");
        return false;
    }
    // 4. D
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById( 1 );
        return false;
    }
}
