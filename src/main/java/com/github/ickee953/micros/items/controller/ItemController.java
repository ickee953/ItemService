package com.github.ickee953.micros.items.controller;

import com.github.ickee953.micros.items.entity.Item;
import com.github.ickee953.micros.items.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController( ItemRepository itemRepository ) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Item>> allItems() {
        return ResponseEntity
                .ok(itemRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createItem(
            @RequestBody Item item,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Item created = itemRepository.save(item);

        return ResponseEntity
                .created(uriComponentsBuilder.path("{itemId}")
                        .build(Map.of("itemId", created.id()))
                ).body(created);
    }
}
