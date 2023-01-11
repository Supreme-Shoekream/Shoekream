package com.supreme.shoekream.model.network.request;


import com.supreme.shoekream.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BuyApiRequest {
    private String type;
    private Long price;
    private int period;
    private Long usePoint;
    private String cardInfo;
    private String receiver;
    private String receiver_hp;
    private String receiver_address;
    private String delivery_memo;
}
