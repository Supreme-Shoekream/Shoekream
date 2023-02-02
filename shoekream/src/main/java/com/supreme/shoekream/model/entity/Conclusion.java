package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.config.Auditable;
import com.supreme.shoekream.model.config.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
//@EqualsAndHashCode(callSuper = true)
//@EntityListeners(AuditingEntityListener.class)
public class Conclusion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long idx; // 번호
    //    private Long sellIdx; // 판매 번호
    //    private Long buyIdx; // 구매 번호
    // 상품명(사이즈), 체결 가격, 체결 날짜 (그래프 뽑는 용인 정보)
    // 상품이랑 체결내역은 게시글과 댓글 관계와 유사

    @ManyToOne
    @JoinColumn(name="product_idx")
    private Product product;

    private String price;

    private LocalDateTime createdAt; // 체결 날짜

    private Conclusion(Product product,String price,LocalDateTime createdAt){
        this.product = product;
        this.price =price;
        this.createdAt=createdAt;
    }

    public static Conclusion of(Product product,String price,LocalDateTime createdAt){
        return new Conclusion(product,price,createdAt);
    }
}
