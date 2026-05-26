package com.clouddine.orderservice.dto.response;

import com.clouddine.orderservice.entity.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private UUID orderId;

    private UUID userId;

    private OrderStatus status;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<OrderItemResponse> items;
}