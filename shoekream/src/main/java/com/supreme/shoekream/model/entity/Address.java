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

    @ManyToOne
    @JoinColumn(name="member_idx")
    private Member member;
    private boolean addressBasic;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Address(String name, String hp, String zipcode, String address1,
                    String address2, boolean addressBasic, Member member) {
        this.name=name;
        this.hp=hp;
        this.zipcode=zipcode;
        this.address1=address1;
        this.address2=address2;
        this.addressBasic=addressBasic;
        this.member=member;
    }

    public static Address of(String name, String hp, String zipcode, String address1
    ,String address2, boolean addressBasic, Member member) {
        return new Address(name, hp, zipcode, address1, address2, addressBasic, member);
    }
}
