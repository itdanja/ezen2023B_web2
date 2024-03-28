package ezenweb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners( AuditingEntityListener.class )
@Getter
public class BaseTime {

    // 1.레코드/엔티티 등록날짜
    @CreatedDate // default now()
    @Column(columnDefinition = " datetime default now() ")
    private LocalDateTime cdate;

    // 2.레코드/엔티티 수정날짜
    @LastModifiedDate // 마지막 수정 날짜
    @Column(columnDefinition = " datetime default now() ")
    private LocalDateTime udate;
}
/*
    상속 : 여러곳에서 공통적인 멤버들

        BaseTime : 주로 엔티티의 생성/수정 일시를 감지해서 자동으로 업데이트 해주는 클래스
    어노테이션
        1. @CreatedDate         : 엔티티가 생성될때 시간이 자동 저장/주입
        2. @LastModifiedDate    : 엔티티가 변경될때 시간이 자동 저장/주입
        3. @MappedSuperclass    : JPA 엔티티 클래스들의 공통 필드 상속 할때 사용하는 어노테이션[ 부모클래스의 매핑정보를 자식클래스에게 제공 ]
        4. @EntityListeners( AuditingEntityListener.class ) : 해당 클래스에서 엔티티 감지 기능
            - @EntityListeners : 엔티티에서 특정 이벤트[DML->SERVICE]가 발생 할때마다 특정 로직 실행
            - AuditingEntityListener.class : 감지 이벤트 실행
                - insert[@CreatedDate] 와 update[@LastModifiedDate] 할때
            + @EnableJpaAuditing : Spring data JPA Auditing 을 이용한 엔티티의 감지.
                - @SpringBootApplication 어노테이션 같은 위치에서 선언

 */
