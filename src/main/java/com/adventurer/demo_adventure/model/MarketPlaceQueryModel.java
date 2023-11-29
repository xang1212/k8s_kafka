package com.adventurer.demo_adventure.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MarketPlaceQueryModel {
    private int mpId;
    private int adventureSellerId;
    private String adventureSellerName;
    private int itemId;
    private String itemName;
    private String status;
    private int adventureBuyerId;
    private String adventureBuyerName;
    private int notiId;
    private String message;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;
    public MarketPlaceQueryModel() {
        this.adventureBuyerId = 0;
        this.adventureBuyerName = null;
    }
}
