package com.adventurer.demo_adventure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    private String name;
    private BigDecimal price;
    private LocalDateTime createdAt;

    private LocalDateTime updateAt;
    @ManyToOne
    private AdventureEntity adventureId;
}
