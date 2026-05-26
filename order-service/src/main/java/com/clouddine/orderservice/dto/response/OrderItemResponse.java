package com.clouddine.orderservice.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {

    private UUID menuItemId;

    private Integer quantity;

    private BigDecimal price;
}

