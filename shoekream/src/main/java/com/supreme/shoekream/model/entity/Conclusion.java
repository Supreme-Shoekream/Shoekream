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
public class Conclusion extends BaseEntity implements Auditable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long idx; // 번호
    private Long sell_idx; // 판매 번호
    private Long buy_idx; // 구매 번호
    //    private LocalDateTime reg_date; // 체결 날짜
}
