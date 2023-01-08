package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.config.Auditable;
import com.supreme.shoekream.model.config.BaseEntity;
import com.supreme.shoekream.model.enumclass.Gender;
import com.supreme.shoekream.model.enumclass.Status;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity             //DB에 만들 테이블임!
@NoArgsConstructor  //매개변수 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@Data               //getter,setter
@Builder             //method.method...가능하게 만들어주는
@ToString(callSuper = true) //부모의 toString을 사용하기 위해
@EqualsAndHashCode(callSuper = true)//Generating equals/hashCode 에러 없애는 방법
@EntityListeners(AuditingEntityListener.class)//이벤트리스너(crud되기전후에 이벤트 발생)
public class Member extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String memberId;
    private String memberPw;
    private String name;
    private String hp;
    private String email;
    @Enumerated(EnumType.STRING) private Status status;
    private String shoeSize;
    private Long point;
    //    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;
    @Enumerated(EnumType.STRING) private Gender gender;
    private String birthDate;
    private String profileMemo;
    private String imgUrl;
}
