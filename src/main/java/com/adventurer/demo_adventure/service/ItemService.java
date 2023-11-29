package com.adventurer.demo_adventure.service;

import com.adventurer.demo_adventure.model.AdventureModel;
import com.adventurer.demo_adventure.model.ItemModel;
import com.adventurer.demo_adventure.model.ResponseModel;
import com.adventurer.demo_adventure.repository.ItemNativeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    public ItemService(ItemNativeRepository itemNativeRepository
    ){
        this.itemNativeRepository = itemNativeRepository;
    }

    private ItemNativeRepository itemNativeRepository;

    public ResponseModel<Integer> insertItem(ItemModel itemModel) {
        ResponseModel<Integer> result = new ResponseModel<>();

        try {
            int insertedRows = this.itemNativeRepository.insertItem(itemModel);
            result.setStatust(201);
            result.setDescription("Item inserted successfully.");
            result.setData(insertedRows);
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
        }

        return result;
    }

    public ResponseModel<Integer> updateItem(ItemModel itemModel) {
        ResponseModel<Integer> result = new ResponseModel<>();

        try {
            int updatedRows = this.itemNativeRepository.updateItem(itemModel);
            if (updatedRows > 0) {
                result.setStatust(200);
                result.setDescription("item updated successfully.");
                result.setData(updatedRows);
            } else {
                result.setStatust(404);
                result.setDescription("item not found with adventureId: " + itemModel.getItemId());
            }
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    public ResponseModel<List<ItemModel>> getAllItemsResponse() {
        ResponseModel<List<ItemModel>> result = new ResponseModel<>();

        try {
            List<ItemModel> items = itemNativeRepository.getAllItems();

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

    public ResponseModel<List<ItemModel>> getItemsByAdventureResponse(int adventureId) {
        ResponseModel<List<ItemModel>> result = new ResponseModel<>();

        try {
            List<ItemModel> items = itemNativeRepository.getItemsByAdventureId(adventureId);

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

    public ResponseModel<Integer> deleteItemByIdResponse(int itemId) {
        ResponseModel<Integer> result = new ResponseModel<>();

        try {
            int deletedRows = itemNativeRepository.deleteItemById(itemId);

            if (deletedRows > 0) {
                result.setStatust(200);
                result.setDescription("item deleted successfully");
                result.setData(deletedRows);
            } else {
                result.setStatust(404);
                result.setDescription("item not found");
                result.setData(0);
            }
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
            result.setData(0);
        }

        return result;
    }
}
