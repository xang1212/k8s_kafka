package com.adventurer.demo_adventure.model;

import lombok.Data;

@Data
public class ResponseModel<T> {
    private int statust;
    private String description;
    private T data;
}
