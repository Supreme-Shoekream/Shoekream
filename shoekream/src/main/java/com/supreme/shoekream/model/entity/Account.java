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
public class Account extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String bank;
    private String accountNumber;
    private String name;
    @OneToOne
    @JoinColumn(name = "member_idx")
    private Member member;
    private LocalDateTime createdAt;

    public Account(Long idx, String bank, String accountNumber,
                   String name, Member member){
        this.idx=idx;
        this.bank=bank;
        this.accountNumber=accountNumber;
        this.name=name;
        this.member=member;
    }

    public static Account of(Long idx, String bank, String accountNumber,
                             String name, Member member){
        return new Account(idx, bank, accountNumber, name, member);
    }
}
