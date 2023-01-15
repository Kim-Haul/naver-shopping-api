package com.example.shoppingapi.models;

import lombok.Getter;

@Getter // 멤버 변수를 private 로 선언하면 해당 녀석을 생성하고 가져올 때 문제가 생기기 때문에, Getter 필요 .
public class ProductRequestDto {
    private String title;
    private String link;
    private String image;
    private int lprice;
}
