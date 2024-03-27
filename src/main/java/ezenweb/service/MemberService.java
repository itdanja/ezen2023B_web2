package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MemberService {
    @Autowired MemberEntityRepository memberEntityRepository;
    public boolean doSignupPost(  MemberDto memberDto){ System.out.println("memberDto = " + memberDto);
        // --  Dao 아닌 엔티티 이용한 레코드 저장하는 방법
        // 1. 엔티티를 만든다.
        // 2. 리포지토리 통한 엔티티를 저장한다. ( 엔티티 저장 성공시 매핑된 엔티티 반환 )
        MemberEntity savedEntity = memberEntityRepository.save(memberDto.toEntity()); // insert
        // 3. 엔티티 생성이 되었는지 아닌지 확인 ( PK )
        if( savedEntity.getMno() > 0 ) return true;
        return false;
    }
}
