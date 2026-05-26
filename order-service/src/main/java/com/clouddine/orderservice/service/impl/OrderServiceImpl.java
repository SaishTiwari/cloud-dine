package com.clouddine.orderservice.service.impl;

import com.clouddine.orderservice.dto.request.CreateOrderRequest;
import com.clouddine.orderservice.dto.request.OrderItemRequest;
import com.clouddine.orderservice.dto.response.OrderItemResponse;
import com.clouddine.orderservice.dto.response.OrderResponse;
import com.clouddine.orderservice.entity.Order;
import com.clouddine.orderservice.entity.OrderItem;
import com.clouddine.orderservice.entity.OrderStatus;
import com.clouddine.orderservice.exception.ResourceNotFoundException;
import com.clouddine.orderservice.repository.OrderRepository;
import com.clouddine.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    // ---------------- CREATE ORDER ----------------
    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {

        Order order = Order.builder()
                .userId(request.getUserId())
                .status(OrderStatus.PENDING)
                .build();

        List<OrderItem> items = request.getItems()
                .stream()
                .map(item -> mapToOrderItem(item, order))
                .toList();

        order.getItems().addAll(items);

        BigDecimal total = items.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(total);

        Order saved = orderRepository.save(order);

        return mapToOrderResponse(saved);
    }

    // ---------------- GET BY ID ----------------
    @Override
    public OrderResponse getOrderById(UUID orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        return mapToOrderResponse(order);
    }

    // ---------------- GET BY USER ----------------
    @Override
    public List<OrderResponse> getOrdersByUser(UUID userId) {

        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToOrderResponse)
                .toList();
    }

    // ---------------- UPDATE STATUS ----------------
    @Override
    public OrderResponse updateOrderStatus(UUID orderId, String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        order.setStatus(OrderStatus.valueOf(status.toUpperCase()));

        Order updated = orderRepository.save(order);

        return mapToOrderResponse(updated);
    }

    // ---------------- DELETE ORDER ----------------
    @Override
    public void deleteOrder(UUID orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        orderRepository.delete(order);
    }

    // =====================================================
    // MAPPERS
    // =====================================================

    private OrderItem mapToOrderItem(OrderItemRequest request, Order order) {

        return OrderItem.builder()
                .menuItemId(request.getMenuItemId())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .order(order)
                .build();
    }

    private OrderResponse mapToOrderResponse(Order order) {

        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(i -> OrderItemResponse.builder()
                        .menuItemId(i.getMenuItemId())
                        .quantity(i.getQuantity())
                        .price(i.getPrice())
                        .build())
                .toList();

        return OrderResponse.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .items(items)
                .build();
    }
}