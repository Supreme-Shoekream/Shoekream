package com.supreme.admin.model.entity;

import com.supreme.admin.model.config.Auditable;
import com.supreme.admin.model.config.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Follow{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long followerIdx;  // 팔로우 누른 사용자 인덱스, ManyToONe
    private Long followingIdx;  // 팔로우 대상 사용자 인덱스, ManyToONe

}