package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @SuperBuilder @ToString
public class MemberDto extends BaseTimeDto implements UserDetails , OAuth2User {
    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;
    private List<GrantedAuthority> 등급목록;

    // - dto를 엔티티로 변환하는 메소드 // C
    public MemberEntity toEntity(){
        return MemberEntity.builder().mname( this.mname )
                .memail( this.memail )
                .mpassword( new BCryptPasswordEncoder().encode(  this.mpassword ) )
                //  new BCryptPasswordEncoder().encode(  암호화 할 데이터  )
                /* 암호화란 :
                    암호 : 정보를 이해할 수 없도록  = 사람이 이해할 수 없도록
                        - 이해할수 없도록 자기만의 방법으로 변경
                        - 스프링 시큐리티가 제외하는 방법 : bcrypt 암호화 제공
                */
                .build();
        // this ?? : 해당 메소드를 호출한 인스턴스
    }

    @Override
    public String getName() {
        return this.memail;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.등급목록;
    }

    @Override
    public String getPassword() {
        return this.mpassword;
    }

    @Override
    public String getUsername() {
        return this.memail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
