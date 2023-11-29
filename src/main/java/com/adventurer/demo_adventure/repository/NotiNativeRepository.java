package com.adventurer.demo_adventure.repository;

import com.adventurer.demo_adventure.model.ItemModel;
import com.adventurer.demo_adventure.model.NotiModel;

import java.util.List;

public interface NotiNativeRepository {
    int insertNoti(NotiModel notiModel);
    List<NotiModel> getAllNoti();
    int deleteNotiById(int notiId);

    int updateNoti(NotiModel notiModel);
}
