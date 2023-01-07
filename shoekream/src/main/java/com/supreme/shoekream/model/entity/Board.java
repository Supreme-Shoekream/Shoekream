package com.supreme.shoekream.model.entity;


import com.supreme.shoekream.model.config.Auditable;
import com.supreme.shoekream.model.config.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Board extends BaseEntity implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long memberIdx;     // ManyToOne
    private String content;
    private String img;
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;

//    @ManyToOne
//    public Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Lk> lks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Reply> replies;
}
