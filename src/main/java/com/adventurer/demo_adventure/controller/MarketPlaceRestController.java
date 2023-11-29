package com.adventurer.demo_adventure.controller;

import com.adventurer.demo_adventure.model.*;
import com.adventurer.demo_adventure.service.ItemService;
import com.adventurer.demo_adventure.service.MarketPlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MarketPlaceRestController {
    public MarketPlaceRestController(MarketPlaceService marketPlaceService) {this.marketPlaceService = marketPlaceService;}

    private MarketPlaceService marketPlaceService;
    @PostMapping("/insert/marketPlace")
    public ResponseModel<Integer> insertMarketPlace(@RequestBody MarketPlaceModel marketPlaceModel) {
        return this.marketPlaceService.insertMarketPlace(marketPlaceModel);
    }

    @GetMapping("/sel-all/marketPlace")
    public ResponseModel<List<MarketPlaceModel>> getAllMarketPlace() {
        return marketPlaceService.getAllMarketPlacesResponse();
    }

    @GetMapping("/sel-all/market-place")
    public ResponseModel<List<MarketPlaceQueryModel>> getAllQueryMarketPlace() {
        return marketPlaceService.getAllQueryMarketPlacesResponse();
    }

    @GetMapping("/market-place/adventure/{adventureSellerId}")
    public ResponseModel<List<MarketPlaceQueryModel>> getMarketPlacesByAventureResponse(@PathVariable int adventureSellerId) {
        return marketPlaceService.getMarketPlacesByAventureResponse(adventureSellerId);
    }

    @PutMapping("/buy-item/marketPlace/{mpId}")
    public ResponseModel<Integer> updateMarketPlace(@PathVariable int mpId, @RequestBody MarketPlaceModel marketPlaceModel) {
        return this.marketPlaceService.updateMarketPlaceByMpId(mpId, marketPlaceModel);
    }

    //getAllMarketPlacesNotSellResponse
    @GetMapping("/sel-all/market-place/in-stock")
    public ResponseModel<List<MarketPlaceQueryModel>> getAllMarketPlacesNotSell() {
        return marketPlaceService.getAllMarketPlacesNotSellResponse();
    }

}
