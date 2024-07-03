package com.github.ickee953.micros.items.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public record Category(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) UUID id
) {
}
