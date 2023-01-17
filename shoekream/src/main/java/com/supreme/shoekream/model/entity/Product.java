package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.dto.ProductDTO;
import lombok.*;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long idx; // 번호
    private String img; // 상품 이미지 주소
    private String brand; // 브랜드
    private String name; // 상품명
    private String nameKor; // 상품명(한글)
    private String size; // 사이즈
//    private String recentPrice; // 최근거래가
//    private String fluctuation; // 가격변동
//    private String nowBuy; // 즉시구매가
//    private String nowSell; // 즉시판매가
//    private String wishCount; // 관심상품수
    private String modelNum; // 모델번호
    private String releaseDate; // 출시일
    private String color; // 컬러
    private String firstPrice; // 발매가
    private String category; // 카테고리
    private String gender; // 성별(남자,여자)
    private String collection; // 컬렉션(메인페이지)

//    private LocalDateTime reg_date; // 등록한 날짜
//    private String createBy; // 등록한 관리자
//    private LocalDateTime update_date; // 수정한 날짜
//    private String updateBy; // 수정한 관리자

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
//    @ToString.Exclude
//    private List<Conclusion> conclusions;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
//    @ToString.Exclude
//    private final Set<Conclusion> conclusions = new LinkedHashSet<>();


    public static Product of(Long idx, String img, String brand, String name, String nameKor, String size, String modelNum, String releaseDate, String color, String firstPrice, String category, String gender, String collection ){
        return new Product(idx, img, brand, name, nameKor, size, modelNum, releaseDate, color, firstPrice, category, gender, collection);
    }

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
