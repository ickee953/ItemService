package com.github.ickee953.micros.core.entity.service;

import com.github.ickee953.micros.core.entity.AbstractDto;
import com.github.ickee953.micros.core.entity.AbstractEntity;
import com.github.ickee953.micros.items.entity.Item;
import com.github.ickee953.micros.core.entity.utils.Result;

import java.util.Optional;
import java.util.UUID;

public interface EntityService<T extends AbstractEntity, V extends AbstractDto> {

    Iterable<T> getAll();

    Item create(V object);

    Optional<T> get(UUID id);

    void delete(UUID id);

    Result<T> replace(UUID id, V object);

}
