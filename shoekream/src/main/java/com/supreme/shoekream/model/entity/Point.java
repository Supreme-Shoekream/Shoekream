package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.config.Auditable;
import com.supreme.shoekream.model.config.BaseEntity;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.enumclass.PointType;
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
public class Point extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long point;
    @Enumerated(EnumType.ORDINAL) private PointType reason;
    private LocalDateTime regDate;
    @ManyToOne
    @JoinColumn(name="member_idx")
    private Member member;
    //    @ManyToOne
//    @JoinColumn(name = "member_idx")
//    private Member member;
    private Point(
            Long point,
            PointType reason,
            LocalDateTime regDate,
            Member member){
        this.point=point;
        this.reason=reason;
        this.regDate=regDate;
        this.member=member;
    }
    public static Point of(
            Long point,
            PointType reason,
            LocalDateTime regDate,
            Member member){
        return new Point(point,reason,regDate,member);
    }
}