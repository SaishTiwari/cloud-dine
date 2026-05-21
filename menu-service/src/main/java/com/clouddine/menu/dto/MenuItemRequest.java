package com.clouddine.menu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuItemRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    private String category;

    private String imageUrl;

    private boolean available;
}