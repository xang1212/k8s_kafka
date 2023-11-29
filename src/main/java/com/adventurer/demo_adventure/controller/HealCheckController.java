package com.adventurer.demo_adventure.controller;

import com.adventurer.demo_adventure.model.AdventureModel;
import com.adventurer.demo_adventure.model.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HealCheckController {
    @GetMapping("/health/check")
    public ResponseModel<Void> getAllAdventures() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setDescription("ok");
        return responseModel;
    }
}
