package com.supreme.shoekream.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Header<T> {    //제네릭을 받는 이유: response / request 같이 받을 수 있어서
    private LocalDateTime transactionTime;
    private String resultCode;
    private T data;             //객체 넣을 곳
    private String description; //에러 발생했을때 넣을 곳
    private Pagination pagination;


    public static <T> Header<T> OK(){
        return (Header<T>)Header.builder().transactionTime(LocalDateTime.now())
                .resultCode("OK").description("정상").build();
        //OK호출시 저걸 리턴시켜줌! 클라이언트가 요청하면 아래처럼 출력
            /*
        json
        {
        transactionTime: 현재시간,
        resultCode: 200,
        desctiption: "정상"
        }
     */
    }


    public static <T> Header<T> OK(T data){
        return (Header<T>)Header.builder().transactionTime(LocalDateTime.now())
                .resultCode("OK").description("정상").data(data).build();
    }

    public static <T> Header<T> ERROR(String description){
        return (Header<T>)Header.builder().transactionTime(LocalDateTime.now())
                .resultCode("Error").description(description).build();
    }

    public static <T> Header<T> OK(T data, Pagination pagination){
        return (Header<T>)Header.builder().transactionTime(LocalDateTime.now())
                .resultCode("OK").description("정상").data(data).pagination(pagination).build();
    }
}
