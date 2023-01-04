package com.supreme.shoekream.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BrandApiResponse {
    private Long idx;
    private String name;
    private Long total_conclusion;
    private String update_by;
    private LocalDateTime update_date;
    private String create_by;
    private LocalDateTime reg_date;
}
