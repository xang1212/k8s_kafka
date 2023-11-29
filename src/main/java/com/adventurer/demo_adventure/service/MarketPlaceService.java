package com.adventurer.demo_adventure.service;

import com.adventurer.demo_adventure.component.kafka.KafkaProducerComponent;
import com.adventurer.demo_adventure.model.MarketPlaceModel;
import com.adventurer.demo_adventure.model.MarketPlaceQueryModel;
import com.adventurer.demo_adventure.model.ResponseModel;
import com.adventurer.demo_adventure.repository.AdventureNativeRepository;
import com.adventurer.demo_adventure.repository.ItemNativeRepository;
import com.adventurer.demo_adventure.repository.MarketPlaceNativeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MarketPlaceService {

    @Value("${kafka.topics.market}")
    private String addMarketTopic;
    public MarketPlaceService(MarketPlaceNativeRepository marketPlaceNativeRepository,
                              AdventureNativeRepository adventureNativeRepository,
                              ItemNativeRepository itemNativeRepository,
                              KafkaProducerComponent kafkaProducerComponent
    ){
        this.marketPlaceNativeRepository = marketPlaceNativeRepository;
        this.adventureNativeRepository = adventureNativeRepository;
        this.itemNativeRepository = itemNativeRepository;
        this.kafkaProducerComponent = kafkaProducerComponent;
    }

    public class InsufficientBalanceException extends RuntimeException {
        public InsufficientBalanceException(String message) {
            super(message);
        }
    }

    private final MarketPlaceNativeRepository marketPlaceNativeRepository;

    private AdventureNativeRepository adventureNativeRepository;

    private ItemNativeRepository itemNativeRepository;

    private KafkaProducerComponent kafkaProducerComponent;
    public ResponseModel<Integer> insertMarketPlace(MarketPlaceModel marketPlaceModel) {
        ResponseModel<Integer> result = new ResponseModel<>();

        try {
            this.objectToJsonString(marketPlaceModel);
            this.kafkaProducerComponent.sendData(marketPlaceModel, this.addMarketTopic);
            result.setStatust(201);
            result.setDescription("market inserted successfully.");

        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    public ResponseModel<List<MarketPlaceModel>> getAllMarketPlacesResponse() {
        ResponseModel<List<MarketPlaceModel>> result = new ResponseModel<>();

        try {
            List<MarketPlaceModel> items = marketPlaceNativeRepository.getAllMarketPlaces();

            result.setStatust(200);
            result.setDescription("OK");
            result.setData(items);
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
            result.setData(null);
        }
        return result;
    }

    public ResponseModel<List<MarketPlaceQueryModel>> getMarketPlacesByAventureResponse(int adventureSellerId) {
        ResponseModel<List<MarketPlaceQueryModel>> result = new ResponseModel<>();

        try {
            List<MarketPlaceQueryModel> items = marketPlaceNativeRepository.getMarketPlacesByAdventure(adventureSellerId);

            result.setStatust(200);
            result.setDescription("OK");
            result.setData(items);
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
            result.setData(null);
        }

        return result;
    }

    public ResponseModel<List<MarketPlaceQueryModel>> getAllQueryMarketPlacesResponse() {
        ResponseModel<List<MarketPlaceQueryModel>> result = new ResponseModel<>();

        try {
            List<MarketPlaceQueryModel> items = marketPlaceNativeRepository.getAllNativeMarketPlaces();

            result.setStatust(200);
            result.setDescription("OK");
            result.setData(items);
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
            result.setData(null);
        }
        return result;
    }

    //getAllMarketPlacesNotSell
    public ResponseModel<List<MarketPlaceQueryModel>> getAllMarketPlacesNotSellResponse() {
        ResponseModel<List<MarketPlaceQueryModel>> result = new ResponseModel<>();

        try {
            List<MarketPlaceQueryModel> items = marketPlaceNativeRepository.getAllMarketPlacesNotSell();

            result.setStatust(200);
            result.setDescription("OK");
            result.setData(items);
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
            result.setData(null);
        }
        return result;
    }

    @Transactional
    public BigDecimal getAdventureBalance(int adventureId) {
        try {
            return adventureNativeRepository.getAdventureBalance(adventureId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get adventure balance.");
        }
    }

    @Transactional
    public ResponseModel<Integer> updateMarketPlaceByMpId(int mpId, MarketPlaceModel marketPlaceModel) {
        ResponseModel<Integer> result = new ResponseModel<>();

        try {
            MarketPlaceModel existingMarketPlace = this.marketPlaceNativeRepository.getMarketPlaceByMpId(mpId);

            if (existingMarketPlace == null) {
                throw new RuntimeException("Market place not found.");
            }

            BigDecimal adventureBuyerBalance = getAdventureBalance(marketPlaceModel.getAdventureBuyerId());

            BigDecimal price = itemNativeRepository.getItemPrice(existingMarketPlace.getItemId());

            if (adventureBuyerBalance.compareTo(price) < 0) {
                throw new InsufficientBalanceException("Your balance is not enough.");
            }

            marketPlaceModel.setAdventureSellerId(existingMarketPlace.getAdventureSellerId());
            marketPlaceModel.setItemId(existingMarketPlace.getItemId());
            int updatedRows = this.marketPlaceNativeRepository.updateMarketPlaceByMpId(mpId, marketPlaceModel);
//            String message = this.objectToJsonString(marketPlaceModel);
//            this.kafkaProducerComponent.sendData(message, this.addMarketTopic);

            result.setStatust(200);
            result.setDescription("buy item successfully.");
            result.setData(updatedRows);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("buy item error {}", e.getMessage());
        }
        return result;
    }

    private String objectToJsonString(Object model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(model);
    }
}
