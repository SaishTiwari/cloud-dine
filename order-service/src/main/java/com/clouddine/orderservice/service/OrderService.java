package com.clouddine.orderservice.service;

import com.clouddine.orderservice.dto.request.CreateOrderRequest;
import com.clouddine.orderservice.dto.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse getOrderById(UUID orderId);

    List<OrderResponse> getOrdersByUser(UUID userId);

    OrderResponse updateOrderStatus(UUID orderId, String status);

    void deleteOrder(UUID orderId);
}