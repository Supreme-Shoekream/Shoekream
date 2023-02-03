package com.supreme.admin.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.supreme.admin.model.config.Auditable;
import com.supreme.admin.model.config.BaseEntity;
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

    //    private Long memberIdx;     // ManyToOne
    private String content;
    private String img;
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;
    private String hashtag;

    @ManyToOne
    @JoinColumn(name="member_idx")
    public Member member;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private List<Lk> lks;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private List<Reply> replies;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private List<Tag> tags;
}
