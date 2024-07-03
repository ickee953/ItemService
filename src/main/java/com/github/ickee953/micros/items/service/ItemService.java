package com.github.ickee953.micros.items.service;

import com.github.ickee953.micros.items.dto.ItemDto;
import com.github.ickee953.micros.items.entity.Item;
import com.github.ickee953.micros.items.utils.Result;

import java.util.Optional;
import java.util.UUID;

public interface ItemService {

    Iterable<Item> getAll();

    Item create(ItemDto item);

    Optional<Item> get(UUID id);

    void delete(UUID id);

    Result<Item> replace(UUID id, ItemDto item);

}
