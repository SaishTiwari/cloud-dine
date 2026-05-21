package com.clouddine.menu.repository;

import com.clouddine.menu.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
}