package com.adventurer.demo_adventure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class NotiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notiId;

    private String message;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;
}
