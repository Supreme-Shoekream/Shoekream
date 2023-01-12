package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.entity.Sell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BuyApiResponse {
    private Long idx;
    private String type;
    private Long price;
    private int period;
    private Long usePoint;
    private String cardInfo;
    private String receiver;
    private String receiver_hp;
    private String receiver_address;
    private String delivery_memo;
    private LocalDateTime createdAt; // 구매 날짜 : now()로 설정 - 거래일시
    private String progress; // 진행 상황(발송완료/입고완료/검수합격/배송완료)
    private String status; // 구매 상태(입찰중/기한만료/진행중/종료)
    private Sell sell;
}
