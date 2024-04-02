package ezenweb.model.repository;

import ezenweb.model.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository // 매핑된 테이블의 엔티티/레코드 들을 조작/관리 하는 리모콘/인터페이스 역할  , 빈 등록
public interface BoardEntityRepository
        extends JpaRepository< BoardEntity , Integer > {



    //@Query( value = "select * from board" , nativeQuery = true )    // == findAll
    // @Query( value = "select * from board where bno = :bno" , nativeQuery = true )    // == findById
    // @Query( value = "select * from board where btitle = :btitle" , nativeQuery = true ) // == findByBtitle
    // @Query( value = "select * from board where bcontent = :keyword" , nativeQuery = true ) // == findByBcontent
    //@Query( value = "select * from board where :key like %:keyword%" , nativeQuery = true ) // == 제목이 포함됨
    @Query( value = " select * from board b inner join member m on b.mno_fk = m.mno where " +
            " if( :keyword = '' , true , " +  // 전체검색  [ 조건1 ]
            " if( :key = 'b.bcontent' , b.bcontent like %:keyword% , " + // [조건2]
            " if( :key = 'm.memail' , m.memail like %:keyword% , true ) ) ) order by b.bno desc" // [조건3]
            , nativeQuery = true )
    Page<Map<Object , Object>> findBySearch(String key , String keyword , Pageable pageable );

}
