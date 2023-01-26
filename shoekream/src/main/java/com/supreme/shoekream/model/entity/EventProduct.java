package com.supreme.shoekream.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class EventProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long idx;
    @ManyToOne
    @JoinColumn(name="product_idx")
    private Product product;
    private String title;
    private String img;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
