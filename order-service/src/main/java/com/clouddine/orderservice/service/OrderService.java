package com.clouddine.orderservice.service;

import com.clouddine.orderservice.dto.request.CreateOrderRequest;
import com.clouddine.orderservice.dto.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse createOrder(UUID userId, CreateOrderRequest request);

    List<OrderResponse> getUserOrders(UUID userId);

    OrderResponse getOrderById(UUID orderId);

    OrderResponse cancelOrder(UUID orderId, UUID userId);
}