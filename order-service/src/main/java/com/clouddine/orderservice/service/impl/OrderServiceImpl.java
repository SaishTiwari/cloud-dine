package com.clouddine.orderservice.service.impl;

import com.clouddine.orderservice.dto.request.CreateOrderRequest;
import com.clouddine.orderservice.dto.request.OrderItemRequest;
import com.clouddine.orderservice.dto.response.OrderItemResponse;
import com.clouddine.orderservice.dto.response.OrderResponse;
import com.clouddine.orderservice.entity.Order;
import com.clouddine.orderservice.entity.OrderItem;
import com.clouddine.orderservice.entity.OrderStatus;
import com.clouddine.orderservice.repository.OrderRepository;
import com.clouddine.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderResponse createOrder(UUID userId, CreateOrderRequest request) {

        log.info("Creating order for user: {}", userId);

        BigDecimal totalAmount = request.getItems()
                .stream()
                .map(item ->
                        item.getPrice()
                                .multiply(BigDecimal.valueOf(item.getQuantity()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                .userId(userId)
                .status(OrderStatus.PENDING)
                .totalAmount(totalAmount)
                .build();

        List<OrderItem> orderItems = request.getItems()
                .stream()
                .map(itemRequest -> mapToOrderItem(itemRequest, order))
                .toList();

        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        log.info("Order created successfully with ID: {}", savedOrder.getId());

        return mapToOrderResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getUserOrders(UUID userId) {

        log.info("Fetching orders for user: {}", userId);

        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToOrderResponse)
                .toList();
    }

    @Override
    public OrderResponse getOrderById(UUID orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found")
                );

        return mapToOrderResponse(order);
    }

    @Override
    public OrderResponse cancelOrder(UUID orderId, UUID userId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found")
                );

        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException("Delivered orders cannot be cancelled");
        }

        order.setStatus(OrderStatus.CANCELLED);

        Order updatedOrder = orderRepository.save(order);

        log.info("Order cancelled: {}", updatedOrder.getId());

        return mapToOrderResponse(updatedOrder);
    }

    private OrderItem mapToOrderItem(OrderItemRequest request, Order order) {

        return OrderItem.builder()
                .menuItemId(request.getMenuItemId())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .order(order)
                .build();
    }

    private OrderResponse mapToOrderResponse(Order order) {

        List<OrderItemResponse> itemResponses = order.getItems()
                .stream()
                .map(item -> OrderItemResponse.builder()
                        .menuItemId(item.getMenuItemId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build())
                .toList();

        return OrderResponse.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .items(itemResponses)
                .build();
    }
}