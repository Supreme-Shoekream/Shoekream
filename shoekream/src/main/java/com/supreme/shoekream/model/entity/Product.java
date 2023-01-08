package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.config.Auditable;
import com.supreme.shoekream.model.config.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity                                        // DB에 만들 테이블임!
@NoArgsConstructor                             // 매개변수 없는 기본 생성자 생성
@AllArgsConstructor                            // 모든 필드 값을 파라미터로 받는 생성자 생성
@Data                                          // getter,setter
@Builder                                       // method.method...가능하게 만들어주는
@ToString                   // 부모의 toString을 사용하기 위해
//@EqualsAndHashCode(callSuper = true)           // Generating equals/hashCode 에러 없애는 방법
//@EntityListeners(AuditingEntityListener.class) // 이벤트리스너(crud되기전후에 이벤트 발생)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; // 번호

    private String name; // 상품명
    private String nameKor; // 상품명(한글)
    private String img; // 상품 이미지 주소

//    private String brand; // 브랜드

    @ManyToOne
    @JoinColumn(name="brand_idx")
    private Brand brand;

    private String size; // 사이즈
    private String category; // 카테고리
    private Long wishCount; // 관심상품수
    private String modelNum; // 모델번호
    private String releaseDate; // 출시일
    private String color; // 컬러
    private String firstPrice; // 발매가
//    private LocalDateTime reg_date; // 등록한 날짜
//    private String createBy; // 등록한 관리자
//    private LocalDateTime update_date; // 수정한 날짜
//    private String updateBy; // 수정한 관리자

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Conclusion> conclusions;
}








//package com.supreme.shoekream.model.entity;
//
//import com.supreme.shoekream.model.config.Auditable;
//import com.supreme.shoekream.model.config.BaseEntity;
//import lombok.*;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//
//@Entity                                        // DB에 만들 테이블임!
//@NoArgsConstructor                             // 매개변수 없는 기본 생성자 생성
//@AllArgsConstructor                            // 모든 필드 값을 파라미터로 받는 생성자 생성
//@Data                                          // getter,setter
//@Builder                                       // method.method...가능하게 만들어주는
//@ToString                   // 부모의 toString을 사용하기 위해
////@EqualsAndHashCode(callSuper = true)           // Generating equals/hashCode 에러 없애는 방법
////@EntityListeners(AuditingEntityListener.class) // 이벤트리스너(crud되기전후에 이벤트 발생)
//public class Product {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idx; // 번호
//
//    private String name; // 상품명
//    private String nameKor; // 상품명(한글)
//    private String img; // 상품 이미지 주소
//
//    private String brand; // 브랜드
//
//    private String size; // 사이즈
//    private String category; // 카테고리
//    private Long wishCount; // 관심상품수
//    private String modelNum; // 모델번호
//    private String releaseDate; // 출시일
//    private String color; // 컬러
//    private String firstPrice; // 발매가
////    private LocalDateTime reg_date; // 등록한 날짜
////    private String createBy; // 등록한 관리자
////    private LocalDateTime update_date; // 수정한 날짜
////    private String updateBy; // 수정한 관리자
//}
