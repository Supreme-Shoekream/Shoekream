package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.dto.EventDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
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

    private EventProduct(String title, String img, LocalDateTime startTime, LocalDateTime endTime) {
      this.title = title;
      this.img = img;
      this.startTime = startTime;
      this.endTime = endTime;

    }
    public static EventProduct of(
            Long idx,
            String title,
            String img,

            Product product,
            LocalDateTime startTime,
            LocalDateTime endTime
    ){
        return new EventProduct(idx, product, title, img, startTime, endTime);
    }
}
