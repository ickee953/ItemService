package com.github.ickee953.micros.items.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public record Item(
        @Id UUID id
) {

}
