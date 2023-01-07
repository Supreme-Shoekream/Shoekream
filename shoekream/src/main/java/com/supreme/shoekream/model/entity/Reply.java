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
public class Reply extends BaseEntity implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long memberIdx;     //ManyToOne
    private String content;
//    private Long boardIdx;      // ManyToOne
//    private LocalDateTime createdAt;

    @ManyToOne
    private Board board;

//    @ManyToMany
//    private Member member;
}
