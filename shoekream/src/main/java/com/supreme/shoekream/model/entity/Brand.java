package com.supreme.shoekream.model.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity     // JPA에서 정의된 필드들을 DB > Table 생성
@NoArgsConstructor  //매개변수 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@Data               //getter,setter
@Builder             //method.method...가능하게 만들어주는
@ToString(callSuper = true) //부모의 toString을 사용하기 위해
//@EqualsAndHashCode(callSuper = true)//Generating equals/hashCode 에러 없애는 방법
@EntityListeners(AuditingEntityListener.class)//이벤트리스너(crud되기전후에 이벤트 발생)
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;                   // 번호
    private String name;                // 브랜드명
    private Long totalConclusion;      // 총 거래체결 수
    private String modifiedBy;           // 수정한 관리자
    private LocalDateTime modifiedAt;  // 수정 날짜
    private String createdBy;           // 작성자
    private LocalDateTime createdAt;     // 등록날짜
//    private List<Product> products;
}