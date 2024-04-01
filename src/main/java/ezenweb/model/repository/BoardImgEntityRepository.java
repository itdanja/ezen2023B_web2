package ezenweb.model.repository;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.BoardImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 매핑된 테이블의 엔티티/레코드 들을 조작/관리 하는 리모콘/인터페이스 역할  , 빈 등록
public interface BoardImgEntityRepository
        extends JpaRepository<BoardImgEntity, String > { }
