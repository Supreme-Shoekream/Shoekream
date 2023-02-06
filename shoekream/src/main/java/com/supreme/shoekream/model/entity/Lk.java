package com.supreme.shoekream.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Lk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @OneToOne
    private Member member;
    @ManyToOne
    @JoinColumn(name="board_idx")
    private Board board;
}
