package com.clouddine.orderservice.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequest {

    private UUID menuItemId;

    private Integer quantity;

    private BigDecimal price;
}