/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.service;

import com.github.ickee953.micros.core.entity.service.EntityService;
import com.github.ickee953.micros.items.dto.ItemDto;
import com.github.ickee953.micros.items.entity.Category;
import com.github.ickee953.micros.items.entity.Item;
import com.github.ickee953.micros.items.repository.ItemRepository;
import com.github.ickee953.micros.core.entity.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.github.ickee953.micros.core.entity.common.Status.CREATED;
import static com.github.ickee953.micros.core.entity.common.Status.REPLACED;

@Service
@RequiredArgsConstructor
public class ItemService implements EntityService<Item, ItemDto> {

    private final ItemRepository itemRepository;

    private final CategoryService categoryService;

    @Override
    public Iterable<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item create(ItemDto item) {

        List<Category> categories = categoryService.getForObject(item);

        return itemRepository.save(
                new Item()
                        .setTitle(item.getTitle())
                        .setCategory( categories )
                        .setCreatedAt(LocalDateTime.now())
        );
    }

    @Override
    public Optional<Item> get(UUID id) {
        return itemRepository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Result<Item> replace(UUID id, ItemDto newItem) {
        List<Category> categories = categoryService.getForObject(newItem);

        return itemRepository.findById(id)
                .map( item -> {
                    item.setTitle(newItem.getTitle());
                    item.setCategory(categories);
                    item.setCreatedAt(newItem.getCreatedAt());

                    return new Result<>(REPLACED, itemRepository.save(item));
                })
                .orElseGet(()-> new Result<>(
                        CREATED,
                        itemRepository.save(
                            new Item()
                                .setTitle(newItem.getTitle())
                                .setCategory(categories)
                                .setCreatedAt(LocalDateTime.now())
                        )
                ));
    }
}
