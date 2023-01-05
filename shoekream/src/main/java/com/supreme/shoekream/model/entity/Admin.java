package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.config.Auditable;
import com.supreme.shoekream.model.config.BaseEntity;
import com.supreme.shoekream.model.enumclass.UserStatus;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity             //DB에 만들 테이블임!
@NoArgsConstructor  //매개변수 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@Data               //getter,setter
@Builder             //method.method...가능하게 만들어주는
@ToString(callSuper = true) //부모의 toString을 사용하기 위해
@EqualsAndHashCode(callSuper = true)//Generating equals/hashCode 에러 없애는 방법
@EntityListeners(AuditingEntityListener.class)//이벤트리스너(crud되기전후에 이벤트 발생)
public class Admin extends BaseEntity implements Auditable {    //regDate updateDate쓸때만 적어주시면 됩니다!
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String adminid;
    private String adminpw;
    private String name;
    private String hp;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}


