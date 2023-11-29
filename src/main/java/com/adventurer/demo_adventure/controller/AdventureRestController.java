package com.adventurer.demo_adventure.controller;

import com.adventurer.demo_adventure.model.AdventureModel;
import com.adventurer.demo_adventure.model.ResponseModel;
import com.adventurer.demo_adventure.service.AdventureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdventureRestController {
    public AdventureRestController(AdventureService adventureService) {this.adventureService = adventureService;}

    private AdventureService adventureService;

    @PostMapping("/insert/adventure")
    public ResponseModel<Integer> insertAdventure(
            @RequestBody AdventureModel adventureModel
            ){
        return this.adventureService.insertAdventure(adventureModel);
    }

    @PutMapping("/update/adventure/{adventureId}")
    public ResponseModel<Integer> updateAdventure(
            @PathVariable("adventureId") int adventureId,
            @RequestBody AdventureModel adventureModel
    ) {
        adventureModel.setAdventureId(adventureId);
        return this.adventureService.updateAdventure(adventureModel);
    }

    @GetMapping("/sel-all/adventures")
    public ResponseModel<List<AdventureModel>> getAllAdventures() {
        return adventureService.getAllAdventuresResponse();
    }

    @DeleteMapping("/delete/adventure/{adventureId}")
    public ResponseModel<Integer> deleteAdventureById(@PathVariable int adventureId) {
        return adventureService.deleteAdventureByIdResponse(adventureId);
    }



}
