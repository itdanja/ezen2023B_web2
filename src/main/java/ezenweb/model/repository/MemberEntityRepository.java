package ezenweb.model.repository;

import ezenweb.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity , Integer > {


    MemberEntity findByMemail( String memail );
    boolean existsByMemail( String memail);
    @Query(value = "select * from member where memail = :memail" , nativeQuery = true )
    MemberEntity findByMemailSql( String memail );

    MemberEntity findByMemailAndMpassword( String memail , String mpassword );
    boolean existsByMemailAndMpassword( String memail , String mpassword );
    @Query(value = "select * from member where memail = :memail and mpassword = :mpassword" , nativeQuery = true )
    MemberEntity findByLoginCheckSql( String memail , String mpassword );

    @Query(value = "select * from member m inner join board b on m.mno = b.mno_fk where mno = :mno order by b.cdate desc" , nativeQuery = true )
    List<Map<Object,Object>> findByMyBoardSql(int mno );

}
/*

    - 리포지토리 만드는 방법
    1. @Repository
    2. extends JpaRepository< 조작할엔티티 , PK의필드타입 >

    - 리포지토리 이용한 CRUD 메소드
    1. .save( 엔티티 )     : 해당 엔티티객체를 테이블에 삽입 INSERT
        매개변수 : 저장할 엔티티 , 반환타입 : 성공 또는 실패 엔티티

 */
