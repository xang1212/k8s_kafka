package com.adventurer.demo_adventure.service;

import com.adventurer.demo_adventure.model.ItemModel;
import com.adventurer.demo_adventure.model.NotiModel;
import com.adventurer.demo_adventure.model.ResponseModel;
import com.adventurer.demo_adventure.repository.ItemNativeRepository;
import com.adventurer.demo_adventure.repository.NotiNativeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotiService {
    public NotiService(NotiNativeRepository notiNativeRepository
    ){
        this.notiNativeRepository = notiNativeRepository;
    }

    private NotiNativeRepository notiNativeRepository;

    public ResponseModel<Integer> insertNoti(NotiModel notiModel) {
        ResponseModel<Integer> result = new ResponseModel<>();

        try {
            int insertedRows = this.notiNativeRepository.insertNoti(notiModel);
            result.setStatust(201);
            result.setDescription("noti inserted successfully.");
            result.setData(insertedRows);
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
        }

        return result;
    }

    public ResponseModel<Integer> updateNoti(NotiModel notiModel) {
        ResponseModel<Integer> result = new ResponseModel<>();

        try {
            int updatedRows = this.notiNativeRepository.updateNoti(notiModel);
            if (updatedRows > 0) {
                result.setStatust(200);
                result.setDescription("noti updated successfully.");
                result.setData(updatedRows);
            } else {
                result.setStatust(404);
                result.setDescription("noti not found with noti_id: " + notiModel.getNotiId());
            }
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
        }
        return result;
    }


    public ResponseModel<List<NotiModel>> getAllNotiResponse() {
        ResponseModel<List<NotiModel>> result = new ResponseModel<>();

        try {
            List<NotiModel> noti = notiNativeRepository.getAllNoti();

            result.setStatust(200);
            result.setDescription("OK");
            result.setData(noti);
        } catch (Exception e) {
            result.setStatust(500);
            result.setDescription(e.getMessage());
            result.setData(null);
        }

        return result;
    }

    public ResponseModel<Integer> deleteNotiByIdResponse(int notiId) {
        ResponseModel<Integer> result = new ResponseModel<>();

        try {
            int deletedRows = notiNativeRepository.deleteNotiById(notiId);

            if (deletedRows > 0) {
                result.setStatust(200);
                result.setDescription("noti deleted successfully");
                result.setData(deletedRows);
            } else {
                result.setStatust(404);
                result.setDescription("noti not found");
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
