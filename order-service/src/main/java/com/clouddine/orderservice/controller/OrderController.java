package com.clouddine.orderservice.controller;

import com.clouddine.orderservice.dto.request.CreateOrderRequest;
import com.clouddine.orderservice.dto.response.OrderResponse;
import com.clouddine.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {

        this.orderService = orderService;

    }

    // ---------------- CREATE ORDER ----------------
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    // ---------------- GET ORDER BY ID ----------------
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    // ---------------- GET ORDERS BY USER ----------------
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }

    // ---------------- UPDATE STATUS ----------------
    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponse> updateStatus(
            @PathVariable UUID orderId,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }

    // ---------------- DELETE ORDER ----------------
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}