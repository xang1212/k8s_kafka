package com.adventurer.demo_adventure.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.aspectj.apache.bcel.generic.IINC;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class NotiModel {
    private int notiId;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;

}
