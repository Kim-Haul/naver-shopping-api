package com.example.shoppingapi.models;

import lombok.Getter;
import org.json.JSONObject;

// ItemDto 는 api 호출을 통해 가져온 정보를 클라이언트에 돌려주기 위한 도구
@Getter
public class ItemDto {
    private String title;
    private String link;
    private String image;
    private int lprice;

    public ItemDto(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.lprice = itemJson.getInt("lprice");
    }
}