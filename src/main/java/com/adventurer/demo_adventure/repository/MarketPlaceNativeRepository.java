package com.adventurer.demo_adventure.repository;

import com.adventurer.demo_adventure.entity.MarketPlaceEntity;
import com.adventurer.demo_adventure.model.MarketPlaceModel;
import com.adventurer.demo_adventure.model.MarketPlaceQueryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface MarketPlaceNativeRepository  {
//    int insertMarketPlace(MarketPlaceModel marketPlaceModel) ;
    List<MarketPlaceModel> getAllMarketPlaces();

    List<MarketPlaceQueryModel> getAllNativeMarketPlaces();
    List<MarketPlaceQueryModel> getMarketPlacesByAdventure(int adventureSellerId);

    List<MarketPlaceQueryModel> getAllMarketPlacesNotSell();
    int deleteMarketPlaceById(int mpId);

    int updateMarketPlaceByMpId(int mpId, MarketPlaceModel marketPlaceModel) ;

    MarketPlaceModel getMarketPlaceByMpId(int mpId);
}
