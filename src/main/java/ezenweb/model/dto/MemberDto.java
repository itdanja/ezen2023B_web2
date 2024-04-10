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

    // - dto를 엔티티로 변환하는 메소드 // C
    public MemberEntity toEntity(){
        return MemberEntity.builder()    .mname( this.mname )
                .memail( this.memail ) .mpassword( this.mpassword ) .mrol( this.mrol )
                .build();
        // this ?? : 해당 메소드를 호출한 인스턴스
    }

    // Collection : 컬레션 프레임워크 : set , list , map
    private List<GrantedAuthority> 권한목록 ;
    @Override // 계정 권한 목록 [ 여러개 가능 Collection ]
    public Collection<? extends GrantedAuthority> getAuthorities() { return 권한목록;  }
    @Override // 계정 비밀번호
    public String getPassword() {  return mpassword; }
    @Override // 계정 아이디
    public String getUsername() {  return memail;  }
    @Override // 계정 만료 여부
    public boolean isAccountNonExpired() { return true; }
    @Override // 계정 잠금 여부   true 열림  false 잠김
    public boolean isAccountNonLocked() { return true; }
    @Override // 계정 증명 여부
    public boolean isCredentialsNonExpired() {  return true;   }
    @Override // 계정 활성화 여부
    public boolean isEnabled() { return true;  }

    // ------------------ OAuth2User --------------------- //
    private Map<String, Object> 소셜회원정보;
    @Override // oauth2 회원의 정보
    public Map<String, Object> getAttributes() { return 소셜회원정보; }
    @Override // oauth2 회원의 아이디
    public String getName() { return memail; }


}
