package com.adventurer.demo_adventure.repository;

import com.adventurer.demo_adventure.model.AdventureModel;

import java.math.BigDecimal;
import java.util.List;

public class AdventureNativeRepositoryTest implements AdventureNativeRepository{
    @Override
    public int insertAdventure(AdventureModel adventureModel) {
        return 1;
    }

    @Override
    public List<AdventureModel> getAllAdventures() {
        return null;
    }

    @Override
    public int deleteAdventureById(int adventureId) {
        return 0;
    }

    @Override
    public void updateAdventuresBalance(BigDecimal price, int adventureSellerId, int adventureBuyerId) {

    }

    @Override
    public int updateAdventure(AdventureModel adventureModel) {
        return 0;
    }

    @Override
    public BigDecimal getAdventureBalance(int adventureId) {
        return null;
    }
}
