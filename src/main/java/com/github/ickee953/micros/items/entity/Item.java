package com.github.ickee953.micros.items.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class Item {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        private String title;

        @OneToMany(fetch = FetchType.LAZY)
        private List<Category> category;

        private LocalDateTime createdAt;
}
