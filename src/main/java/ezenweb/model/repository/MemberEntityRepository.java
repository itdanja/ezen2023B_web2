package ezenweb.model.repository;

import ezenweb.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEntityRepository
        extends JpaRepository<MemberEntity , Integer > {
}
/*

    - 리포지토리 만드는 방법
    1. @Repository
    2. extends JpaRepository< 조작할엔티티 , PK의필드타입 >

    - 리포지토리 이용한 CRUD 메소드
    1. .save( 엔티티 )     : 해당 엔티티객체를 테이블에 삽입 INSERT
        매개변수 : 저장할 엔티티 , 반환타입 : 성공 또는 실패 엔티티

 */
