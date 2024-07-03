package com.github.ickee953.micros.items.controller;

import com.github.ickee953.micros.items.dto.CategoryDto;
import com.github.ickee953.micros.items.dto.ItemDto;
import com.github.ickee953.micros.items.entity.Category;
import com.github.ickee953.micros.items.entity.Item;
import com.github.ickee953.micros.items.repository.CategotyRepository;
import com.github.ickee953.micros.items.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    private final CategotyRepository categotyRepository;

    @GetMapping
    public ResponseEntity<Iterable<Item>> allItems() {
        return ResponseEntity
                .ok(itemRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createItem(
            @RequestBody ItemDto item,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        List<Category> categories = categotyRepository.findAllById(
                item.category().stream().map(CategoryDto::id
        ).toList());

        Item created = itemRepository.save(
                new Item()
                        .setTitle(item.title())
                        .setCategory( categories )
                        .setCreatedAt(LocalDateTime.now())
        );

        return ResponseEntity.created(
                    uriComponentsBuilder.path("{itemId}").build(Map.of("itemId", created.getId())))
                .body(created);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> replaceItem(
            @RequestBody ItemDto newItem,
            @PathVariable("id") UUID id,
            UriComponentsBuilder uriComponentsBuilder
    ) {

        List<Category> categories = categotyRepository.findAllById(
                newItem.category().stream().map(CategoryDto::id
        ).toList());

        return itemRepository.findById(id)
                .map( item -> {
                    item.setTitle(newItem.title());
                    item.setCategory(categories);
                    item.setCreatedAt(newItem.createdAt());

                    return ResponseEntity.ok(itemRepository.save(item));
                })
                .orElseGet(()-> {
                    Item created = itemRepository.save(
                            new Item()
                                    .setTitle(newItem.title())
                                    .setCategory(categories)
                                    .setCreatedAt(LocalDateTime.now())
                    );

                    return ResponseEntity.created(
                            uriComponentsBuilder.path("{id}").build(Map.of("id", created.getId()))
                    ).body(created);
                });

    }

    @GetMapping("{id}")
    public ResponseEntity<Item> get(@PathVariable("id") UUID id) {
        return ResponseEntity.of(itemRepository.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") UUID id) {
        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
