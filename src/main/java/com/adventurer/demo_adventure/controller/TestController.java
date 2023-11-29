package com.adventurer.demo_adventure.controller;

import com.adventurer.demo_adventure.model.AdventureModel;
import com.adventurer.demo_adventure.model.ItemModel;
import com.adventurer.demo_adventure.model.MarketPlaceModel;
import com.adventurer.demo_adventure.model.ResponseModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

import org.springframework.web.client.RestTemplate;


import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
//    private RestTemplate restTemplate;
//    public TestController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    @PostMapping("/insert/marketPlace")
//    public ResponseModel<Integer> insertMarketPlace(@RequestBody ItemModel itemModel) {
//        ResponseModel<Integer> response = new ResponseModel<>();
//
//        try {
//            // Transform ItemModel into a JSON request
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<ItemModel> requestEntity = new HttpEntity<>(itemModel, headers);
//
//            // Make a POST request to your own application's endpoint
//            String apiUrl = "http://localhost:8080/api/insert/marketPlace"; // Replace with the actual URL of your endpoint
//
//            ResponseEntity<ResponseModel<Integer>> result = restTemplate.exchange(
//                    apiUrl,
//                    HttpMethod.POST,
//                    requestEntity,
//                    new ParameterizedTypeReference<ResponseModel<Integer>>() {}
//            );
//
//            response.setStatust(result.getStatusCodeValue());
//            response.setDescription(result.getBody().getDescription());
//        } catch (Exception e) {
//            response.setStatust(500);
//            response.setDescription(e.getMessage());
//        }
//
//        return response;
//    }
}
