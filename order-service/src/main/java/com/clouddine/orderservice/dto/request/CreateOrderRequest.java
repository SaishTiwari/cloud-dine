package com.clouddine.orderservice.dto.request;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {

    private UUID userId;

    private List<OrderItemRequest> items;
}