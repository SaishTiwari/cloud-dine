package com.clouddine.orderservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}