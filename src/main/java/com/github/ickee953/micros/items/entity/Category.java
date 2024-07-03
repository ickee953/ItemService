package com.github.ickee953.micros.items.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        private String title;

        @ManyToOne(fetch = FetchType.LAZY)
        private Category parentCategory;

        private LocalDateTime createdAt;
}
