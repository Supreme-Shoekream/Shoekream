package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.config.Auditable;
import com.supreme.shoekream.model.config.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class EventMember extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long idx;
    @ManyToOne
    @JoinColumn(name = "eventProduct_idx")
    private EventProduct eventProduct;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member member;

    private EventMember(Long idx,EventProduct eventProduct,Member member){
        this.idx=idx;
        this.eventProduct=eventProduct;
        this.member=member;
    }

    public static EventMember of(Long idx,EventProduct eventProduct,Member member){
        return new EventMember(idx,eventProduct,member);
    }

    public static EventMember of(EventProduct eventProduct,Member member){
        return EventMember.of(null,eventProduct,member);
    }

}
