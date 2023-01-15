package com.example.shoppingapi.utils;

import com.example.shoppingapi.models.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component // 컴포넌트 등록 (서비스나 컨트롤러, 엔티티처럼 스프링이 필요할 때 알아서 쓸 수 있도록 권한을 부여하는 개념)
public class NaverShopSearch {
    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("X-Naver-Client-Id", "5HBxk7r1Ew8RhBvchPYK");
        headers.add("X-Naver-Client-Secret", "OqT1Th_low");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatusCode = responseEntity.getStatusCode();
        int status = httpStatusCode.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result) {
        // 문자열 정보를 JSONObjet 로 바꾸기
        JSONObject rjson = new JSONObject(result);
        System.out.println("------- 문자열 정보를 JSONObjet 로 바꾸기 -------");
        System.out.println(rjson);
        // JSONObject 에서 items 배열 꺼내기
        JSONArray items = rjson.getJSONArray("items");
        System.out.println("------- JSONObject 에서 items 배열 꺼내기 -------");
        System.out.println(items);
        // JSONArray 로 for 문 돌기
        System.out.println("------- JSONArray 로 for 문 돌기 -------");

        List<ItemDto> itemDtoList = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            System.out.println(itemJson);

            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);

            // JSONObject 에서 원하는 String, int 데이터 뽑기
            // String title = itemJson.getString("title")
            // String image = itemJson.getString("image")
            // String link = itemJson.getString("link")
            // int lprice = itemJson.getInt("lprice")
        }

        return itemDtoList;
    }
    
}