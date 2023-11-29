package com.adventurer.demo_adventure.controller;

import com.adventurer.demo_adventure.model.AdventureModel;
import com.adventurer.demo_adventure.model.ItemModel;
import com.adventurer.demo_adventure.model.NotiModel;
import com.adventurer.demo_adventure.model.ResponseModel;
import com.adventurer.demo_adventure.service.AdventureService;
import com.adventurer.demo_adventure.service.ItemService;
import com.adventurer.demo_adventure.service.NotiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotiRestController {
    public NotiRestController(NotiService notiService) {this.notiService = notiService;}

    private NotiService notiService;

    @PostMapping("/insert/noti")
    public ResponseModel<Integer> insertNoti(
            @RequestBody NotiModel notiModel
    ){
        return this.notiService.insertNoti(notiModel);
    }

    @PutMapping("/update/noti/{notiId}")
    public ResponseModel<Integer> updateNoti(
            @PathVariable("notiId") int notiId,
            @RequestBody NotiModel notiModel
    ) {
        notiModel.setNotiId(notiId);
        return this.notiService.updateNoti(notiModel);
    }

    @GetMapping("/sel-all/noti")
    public ResponseModel<List<NotiModel>> getAllNoti() {
        return notiService.getAllNotiResponse();
    }

    @DeleteMapping("/delete/noti/{notiId}")
    public ResponseModel<Integer> deleteNotiById(@PathVariable int notiId) {
        return notiService.deleteNotiByIdResponse(notiId);
    }
}
