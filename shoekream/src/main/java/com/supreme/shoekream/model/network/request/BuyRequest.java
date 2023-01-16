package com.supreme.shoekream.model.network.request;


import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.model.enumclass.Type;

import java.time.LocalDateTime;

public record BuyRequest(
        Type type,
        Long price,
        int period,
        int usePoint,
        String cardInfo,
        String receiver,
        String receiverHp,
        String receiverAddress,
        String deliveryMemo
) {
    public static BuyRequest of( Type type, Long price, int period,
                                int usePoint, String cardInfo, String receiver, String receiverHp,
                                String receiverAddress, String deliveryMemo){
        return new BuyRequest( type, price, period, usePoint,cardInfo,receiver
                ,receiverHp,receiverAddress,deliveryMemo);
    }

}
