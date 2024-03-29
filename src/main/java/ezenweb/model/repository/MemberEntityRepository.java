package ezenweb.model.repository;

import ezenweb.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MemberEntityRepository
        extends JpaRepository<MemberEntity , Integer > {

    // ============== 아이디 중복검사 =============== //
    // 1. 특정 필드의 조건으로 레코드/엔티티 검색
    MemberEntity findByMemail( String memail );

    // 2. 특정 필드의 조건으로 존재여부 검색
    boolean existsByMemail( String memail );

    // 3. 직접 native SQL 지원 [ dao방식 select * from member where memail = ?; ]
    // @Query(value = "SQL작성",nativeQuery = true)
        // SQL 매개변수 대입하는 방법 : sql문 안에서 :(콜론)뒤에 매개변수 작성한다.
    @Query(value = "select * from member where memail = :memail"
            , nativeQuery = true )
    MemberEntity findByMemailSQL( String memail );

    // ============== --- 로그인 ---- =============== //
    // 1.
    Optional<MemberEntity> findByMemailAndMpassword( String memail , String mpassword);
    // 2.
    boolean existsByMemailAndMpassword( String memail , String mapssword );

    // 3.
    @Query(value = "select * from member " +
            " where memail = :memail and mpassword = :mpassword"
            , nativeQuery = true)
    MemberEntity findByLoginSQL( String memail , String mpassword );

    // ============== ---  내가 쓴글  ---- =============== //
    // 1. 양방향일때는 회원 엔티티를 통해 게시물 호출 가능하다.

    // 2. 단방향일때는 회원 엔티티를 이용한 게시물 호출할때는 조인query
    @Query(value = " select * from member m inner join board b " +
                " on m.mno = b.mno_fk where m.mno = :mno" , nativeQuery = true )
    List<Map<Object,Object>> findByMyBoardSQL(int mno );

    /*
            List : 여러개 레코드
            Map<> : 하나의 레코드
                Map<필드명,필드값> : 하나의 레코드 안에서 필드와 값
            ex)
                mno     mid     mname
                1       qwe     유재석
                2       asd     강호동

            [
                { mno : 1 , mid : 'qwe' , mname : '유재석' } ,
                { mno : 2 , mid : 'asd' , mname : '강호동' }
            ]


     */


}
/*
    추상메소드
        1. 특정 필드의 조건으로 레코드/엔티티 검색
            MemberEntity findBy필드명( 조건매개변수 )
        2. 특정 필드의 조건으로 존재여부 검색
            boolean existsBy필드명( 조건매개변수 );
 */
