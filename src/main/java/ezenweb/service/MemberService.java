package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MemberService {

    @Autowired MemberEntityRepository memberEntityRepository;
    @Autowired HttpServletRequest request;

    public boolean doSignupPost(  MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        // --  Dao 아닌 엔티티 이용한 레코드 저장하는 방법
        // 1. 엔티티를 만든다.
        // 2. 리포지토리 통한 엔티티를 저장한다.
        memberEntityRepository.save( memberDto.toEntity() ); // insert
        return true;
    }

    public boolean doLoginPost(  MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        // 1. 입력받은 데이터[아이디/패스워드] 검증하기
        List<MemberEntity> memberEntities = memberEntityRepository.findAll();
        // 2. 동일한 아이디 / 비밀번호 찾기
        for( int i = 0 ; i < memberEntities.size() ; i++ ) {
            MemberEntity m = memberEntities.get(i);
            // 3. 동일한 데이터 엔티티 찾았다.
            if (m.getMemail().equals(memberDto.getMemail()) &&
                    m.getMpassword().equals(memberDto.getMpassword())) {
                // 4. 세션 부여      // 세션 저장
                request.getSession().setAttribute("loginDto", m.toDto() );
                return true;
            }
        }
        return false;
    }

    public MemberDto doLoginInfo(){
        // 1. 세션 호출
        Object session = request.getSession().getAttribute("loginDto");
        // 2. 세션 검증
        if( session != null ){
            System.out.println("session = " + session);
            return (MemberDto) session;
        }
        return null;
    }

    public boolean doLogout(  ){
        request.getSession().setAttribute( "loginDto" , null );
        return true;
    }

}
