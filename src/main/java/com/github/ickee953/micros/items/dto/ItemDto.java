package com.github.ickee953.micros.items.dto;

import com.github.ickee953.micros.items.entity.Category;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ItemDto(
        UUID id,
        String title,
        List<CategoryDto> category,
        LocalDateTime createdAt
) {
}
