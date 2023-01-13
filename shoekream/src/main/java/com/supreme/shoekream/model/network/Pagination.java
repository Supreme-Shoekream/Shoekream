package com.supreme.shoekream.model.network;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //getter,setter,...
@NoArgsConstructor  // Pagication(){}빈생성자
@AllArgsConstructor // 생성자 다있는거
@Builder    //빌더!
public class Pagination {
    private Integer totalPages;
    private Long totalElements;
    private Integer currentPage;
    private Integer currentElements;

}
