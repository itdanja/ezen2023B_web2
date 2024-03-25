package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    // * 리포지토리 객체
    @Autowired private BoardEntityRepository boardEntityRepository;
    @Autowired private MemberEntityRepository memberEntityRepository;
    @Autowired private ReplyEntityRepository replyEntityRepository;
    @Autowired private HttpServletRequest request;

    public boolean postBoard(MemberDto memberDto  ){
        memberEntityRepository.save( memberDto.toEntity() );
        return true;
    }

    public boolean getBoard( MemberDto memberDto   ){
        // 1. 입력받은 데이터[아이디/패스워드] 검증하기
        List<MemberEntity> memberEntities = memberEntityRepository.findAll();
        // 2. 동일한 아이디 / 비밀번호 찾기
        for( int i = 0 ; i < memberEntities.size() ; i++ ) {
            MemberEntity m = memberEntities.get(i);
            System.out.println("m = " + m);
            // 3. 동일한 데이터 엔티티 찾았다.
            if (m.getMemail().equals(memberDto.getMemail()) &&
                    m.getMpassword().equals(memberDto.getMpassword())) {
                // 4. 세션 부여      // 세션 저장
                request.getSession().setAttribute("loginMno", m.getMno() );
                return true;
            }
        }
        return false;
    }
    public int getLoginMno(){
        Object session = request.getSession().getAttribute("loginMno");
        System.out.println("session.toString() = " + session.toString());
        if( session != null ){
            System.out.println("session = " + session);
            return (Integer) session;
        }
        return 0;
    }

}
