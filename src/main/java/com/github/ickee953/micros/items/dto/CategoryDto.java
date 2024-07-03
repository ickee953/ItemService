package com.github.ickee953.micros.items.dto;

import com.github.ickee953.micros.items.entity.Category;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoryDto(
        UUID id,
        String title,
        CategoryDto parentCategory,
        LocalDateTime createdAt
) {
}
