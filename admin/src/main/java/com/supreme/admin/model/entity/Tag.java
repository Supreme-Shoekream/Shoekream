package com.supreme.admin.model.entity;

import com.supreme.admin.model.config.Auditable;
import com.supreme.admin.model.config.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Tag{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    //    private Long boardIdx;      // ManyToOne
    @ManyToOne
    @JoinColumn(name = "board_idx")
    private Board board;
//    private Long productIdx;    // OneToOne

    @ManyToOne
    @JoinColumn(name="product_idx")
    private Product product;
}
