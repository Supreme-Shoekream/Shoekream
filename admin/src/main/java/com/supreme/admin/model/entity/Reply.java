package com.supreme.admin.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.supreme.admin.model.config.Auditable;
import com.supreme.admin.model.config.BaseEntity;
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

//    private Long memberIdx;     //ManyToOne
    @OneToOne
    @JsonIgnore
    private Member member;
    private String content;
//    private Long boardIdx;      // ManyToOne
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="board_idx")
    private Board board;

//    @ManyToMany
//    private Member member;


    private Reply(Member member, String content, Board board, LocalDateTime createdAt){
        this.member = member;
        this.content = content;
        this.board = board;
        this.createdAt = createdAt;
    }

public static Reply of(Member member, String content, Board board, LocalDateTime createdAt){
    return new Reply(member, content, board, createdAt);
}
}
