package com.github.ickee953.micros.items.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public record Item(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) UUID id,
        String title,
        @OneToMany(fetch = FetchType.LAZY)
        List<Category> category,
        LocalDateTime createdAt
) {

}
