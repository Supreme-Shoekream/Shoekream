package com.supreme.admin.model.entity;

import com.supreme.admin.model.config.Auditable;
import com.supreme.admin.model.config.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class EventProduct extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long idx;
    @ManyToOne
    @JoinColumn(name="product_idx")
    private Product product;
    @ToString.Exclude
    @Nullable
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventProduct", cascade = CascadeType.ALL)
    private List<EventMember> eventMember;
    private String title;
    private String img;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private EventProduct(Product product, String title, String img, LocalDateTime startTime, LocalDateTime endTime) {
      this.product = product;
      this.title = title;
      this.img = img;
      this.startTime = startTime;
      this.endTime = endTime;
    }
    public static EventProduct of(
            Product product,
            String title,
            String img,
            LocalDateTime startTime,
            LocalDateTime endTime
    ){
        return new EventProduct(product, title, img, startTime, endTime);
    }
}
