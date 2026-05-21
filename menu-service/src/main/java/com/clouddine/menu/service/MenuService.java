package com.clouddine.menu.service;

import com.clouddine.menu.dto.MenuItemRequest;
import com.clouddine.menu.entity.MenuItem;
import com.clouddine.menu.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.clouddine.menu.exception.ResourceNotFoundException;
import java.util.UUID;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuItemRepository repository;

    public MenuItem addMenuItem(MenuItemRequest request) {

        MenuItem item = MenuItem.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .imageUrl(request.getImageUrl())
                .available(request.isAvailable())
                .build();

        return repository.save(item);
    }

    public List<MenuItem> getAllMenuItems() {
        return repository.findAll();
    }

    public MenuItem getMenuItemById(UUID id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Menu item not found")
                );
    }

    public MenuItem updateMenuItem(UUID id, MenuItemRequest request) {

        MenuItem item = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Menu item not found")
                );

        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setCategory(request.getCategory());
        item.setImageUrl(request.getImageUrl());
        item.setAvailable(request.isAvailable());

        return repository.save(item);
    }

    public void deleteMenuItem(UUID id) {

        MenuItem item = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Menu item not found")
                );

        repository.delete(item);
    }


}