package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.config.Auditable;
import com.supreme.shoekream.model.config.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Address extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false) private String name;

    @Column(nullable = false) private String hp;
    private String zipcode;
    private String address1;
    private String address2;

//    @ManyToOne
//    @JoinColumn(name="member_idx")
//    private Member member;
    private Long memberIdx;

    private boolean addressBasic;
}
