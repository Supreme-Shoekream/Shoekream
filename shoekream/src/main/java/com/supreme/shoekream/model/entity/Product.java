package com.supreme.shoekream.model.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long idx; // 번호
    private String img; // 상품 이미지 주소
    private String brand; // 브랜드
    private String name; // 상품명
    private String nameKor; // 상품명(한글)
    private String size; // 사이즈
    @ColumnDefault("0")private int wishCount; // 관심상품수
    private String modelNum; // 모델번호
    private String releaseDate; // 출시일
    private String color; // 컬러
    private Long firstPrice; // 발매가
    private String category; // 카테고리
    private String gender; // 성별(남자,여자)
    private String collection; // 컬렉션(메인페이지)


    public static Product of(Long idx, String img, String brand, String name, String nameKor, String size, int wishCount, String modelNum, String releaseDate, String color, Long firstPrice, String category, String gender, String collection){
        return new Product(idx, img, brand, name, nameKor, size, wishCount, modelNum, releaseDate, color, firstPrice, category, gender, collection);
    }

}

