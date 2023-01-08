package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.config.Auditable;
import com.supreme.shoekream.model.config.BaseEntity;
import com.supreme.shoekream.model.enumclass.UserStatus;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Notice extends BaseEntity implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String title;
    private String content;
//    private String createdBy;
    private LocalDateTime createdAt;
//    private String modifiedBy;
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Member createdBy;

    @ManyToOne
    @JoinColumn(name="modified_by")
    private  Member modifiedBy;
}
