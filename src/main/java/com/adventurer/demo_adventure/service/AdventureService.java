package com.adventurer.demo_adventure.service;


import com.adventurer.demo_adventure.model.AdventureModel;
import com.adventurer.demo_adventure.model.ResponseModel;
import com.adventurer.demo_adventure.repository.AdventureNativeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureService {
    public AdventureService(AdventureNativeRepository adventureNativeRepository
    ){
        this.adventureNativeRepository = adventureNativeRepository;
    }

    private AdventureNativeRepository adventureNativeRepository;

    public ResponseModel<Integer> insertAdventure(AdventureModel adventureModel){
        ResponseModel<Integer> result = new ResponseModel<>();
        if (adventureModel.getName() == null || adventureModel.getBalance() == null) {
            result.setStatust(400); // Bad Request
            result.setDescription("Name and balance cannot be null");
            return result;
        }

        result.setStatust(201);
        result.setDescription("ok");
        try {
            // do some business
            int insertedRows = this.adventureNativeRepository.insertAdventure(adventureModel);
            result.setData(insertedRows);
        } catch (Exception e){
            result.setStatust(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    public ResponseModel<List<AdventureModel>> getAllAdventuresResponse() {
        ResponseModel<List<AdventureModel>> result = new ResponseModel<>();

        try {
            List<AdventureModel> adventures = adventureNativeRepository.getAllAdventures();

            result.setStatust(200);
            result.setDescription("OK");
            result.setData(adventures);
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
            result.setData(null);
        }

        return result;
    }

    public ResponseModel<Integer> deleteAdventureByIdResponse(int adventureId) {
        ResponseModel<Integer> result = new ResponseModel<>();

        try {
            int deletedRows = adventureNativeRepository.deleteAdventureById(adventureId);

            if (deletedRows > 0) {
                result.setStatust(200);
                result.setDescription("Adventure deleted successfully");
                result.setData(deletedRows);
            } else {
                result.setStatust(404);
                result.setDescription("Adventure not found");
                result.setData(0);
            }
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
            result.setData(0);
        }

        return result;
    }

    public ResponseModel<Integer> updateAdventure(AdventureModel adventureModel) {
        ResponseModel<Integer> result = new ResponseModel<>();

        try {
            int updatedRows = this.adventureNativeRepository.updateAdventure(adventureModel);
            if (updatedRows > 0) {
                result.setStatust(200);
                result.setDescription("Adventure updated successfully.");
                result.setData(updatedRows);
            } else {
                result.setStatust(404);
                result.setDescription("Adventure not found with adventureId: " + adventureModel.getAdventureId());
            }
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }



}
