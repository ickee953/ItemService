package com.github.ickee953.micros.items.repository;

import com.github.ickee953.micros.items.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategotyRepository extends JpaRepository<Category, UUID> {
}
