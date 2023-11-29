package com.adventurer.demo_adventure.repository;

import com.adventurer.demo_adventure.model.AdventureModel;
import com.adventurer.demo_adventure.model.ItemModel;

import java.math.BigDecimal;
import java.util.List;

public interface ItemNativeRepository {
    int insertItem(ItemModel itemModel);
    List<ItemModel> getAllItems();
    List<ItemModel> getItemsByAdventureId(int adventureId);
    int deleteItemById(int itemId);

    int updateItem(ItemModel itemModel);

    BigDecimal getItemPrice(int itemId);

    int updateItems(int itemId, int adventureBuyerId);
}
