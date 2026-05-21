package com.clouddine.menu.controller;

import com.clouddine.menu.dto.MenuItemRequest;
import com.clouddine.menu.entity.MenuItem;
import com.clouddine.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PreAuthorize ("hasRole('ADMIN')")
    @PostMapping
    public MenuItem addMenuItem(
            @Valid @RequestBody MenuItemRequest request
    ) {
        return menuService.addMenuItem(request);
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItemById(
            @PathVariable UUID id
    ) {
        return menuService.getMenuItemById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public MenuItem updateMenuItem(
            @PathVariable UUID id,
            @Valid @RequestBody MenuItemRequest request
    ) {
        return menuService.updateMenuItem(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteMenuItem(
            @PathVariable UUID id
    ) {
        menuService.deleteMenuItem(id);

        return "Menu item deleted successfully";
    }



}