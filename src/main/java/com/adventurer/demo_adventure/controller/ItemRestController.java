package com.adventurer.demo_adventure.controller;

import com.adventurer.demo_adventure.model.AdventureModel;
import com.adventurer.demo_adventure.model.ItemModel;
import com.adventurer.demo_adventure.model.ResponseModel;
import com.adventurer.demo_adventure.service.AdventureService;
import com.adventurer.demo_adventure.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemRestController {
    public ItemRestController(ItemService itemService) {this.itemService = itemService;}

    private ItemService itemService;
    @PostMapping("/insert/item")
    public ResponseModel<Integer> insertItem(@RequestBody ItemModel itemModel) {
        return this.itemService.insertItem(itemModel);
    }

    @GetMapping("/sel-all/items")
    public ResponseModel<List<ItemModel>> getAllItems() {
        return itemService.getAllItemsResponse();
    }

    @PutMapping("/update/item/{itemId}")
    public ResponseModel<Integer> updateItem(
            @PathVariable("itemId") int itemId,
            @RequestBody ItemModel itemModel
    ) {
        itemModel.setItemId(itemId);
        return this.itemService.updateItem(itemModel);
    }

    @DeleteMapping("/delete/item/{itemId}")
    public ResponseModel<Integer> deleteItemById(@PathVariable int itemId) {
        return itemService.deleteItemByIdResponse(itemId);
    }

    //getItemsByAdventureResponse
    @GetMapping("/adventure/items/{adventureId}")
    public ResponseModel<List<ItemModel>> getItemByAdventure(@PathVariable int adventureId){
        return itemService.getItemsByAdventureResponse(adventureId);
    }
}
