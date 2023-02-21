package com.domain.dto;

import jakarta.validation.constraints.NotEmpty;
//Class DTO
public class CategoryData {
    @NotEmpty(message = "Name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
