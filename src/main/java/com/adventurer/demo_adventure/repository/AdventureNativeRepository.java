package com.adventurer.demo_adventure.repository;

import com.adventurer.demo_adventure.model.AdventureModel;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


public interface AdventureNativeRepository {

    int insertAdventure(AdventureModel adventureModel);
    List<AdventureModel> getAllAdventures();
    int deleteAdventureById(int adventureId);
    public void updateAdventuresBalance(BigDecimal price, int adventureSellerId, int adventureBuyerId);
    public int updateAdventure(AdventureModel adventureModel);
    BigDecimal getAdventureBalance(int adventureId);



}
