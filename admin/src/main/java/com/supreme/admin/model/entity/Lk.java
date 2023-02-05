package com.supreme.admin.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
//@EqualsAndHashCode(callSuper = true)
//@EntityListeners(AuditingEntityListener.class)
public class Lk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    //    private Long boardIdx;   // 나중에 OneToOne 처리
//    private Long memberIdx;     // 나중에 ManyToONe 처리

    @OneToOne
    private Member member;
//    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="board_idx")
    private Board board;
}
