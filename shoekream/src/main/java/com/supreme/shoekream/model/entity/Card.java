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
public class Card extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String cardType;
    private LocalDateTime createdAt;
    private String cardNumber;
    @ManyToOne
    @JoinColumn(name="member_idx")
    private Member member;
    private String cardMm;
    private String cardYy;
    private String cardPw;
    private String birthDate;
    private boolean cardBasic;

    private Card(Member member, String cardType, String cardNumber,
                 String cardMm, String cardYy, String cardPw, String birthDate, boolean cardBasic){
        this.member = member;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardMm = cardMm;
        this.cardYy = cardYy;
        this.cardPw = cardPw;
        this.birthDate = birthDate;
        this.cardBasic = cardBasic;
    }

    public static Card of(Member member, String cardType, String cardNumber,
                          String cardMm, String cardYy, String cardPw, String birthDate, boolean cardBasic) {
        return new Card(member,cardType,cardNumber,cardMm,cardYy,cardPw,birthDate, cardBasic);
    }
}

