package com.supreme.shoekream.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
//@EqualsAndHashCode(callSuper = true)
//@EntityListeners(AuditingEntityListener.class)
public class Wish {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long idx;

//    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="member_idx")
    private Member member;

//    @ManyToOne
//    @JoinColumn(name="product_idx")
//    private Product product;

    private Long productIdx;

//    private String size;
}
