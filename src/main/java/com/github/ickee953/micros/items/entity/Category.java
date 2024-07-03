package com.github.ickee953.micros.items.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public record Category(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) UUID id,
        String title,
        @ManyToOne(fetch = FetchType.LAZY)
        Category parentCategory,
        LocalDateTime createdAt
) {
}
