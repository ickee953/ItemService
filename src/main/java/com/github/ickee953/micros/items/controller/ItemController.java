/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.controller;

import com.github.ickee953.micros.core.entity.service.EntityService;
import com.github.ickee953.micros.items.dto.ItemDto;
import com.github.ickee953.micros.items.entity.Item;
import com.github.ickee953.micros.core.entity.utils.Result;
import com.github.ickee953.micros.core.entity.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final EntityService<Item, ItemDto> itemService;

    @GetMapping
    public ResponseEntity<Iterable<Item>> allItems() {
        Iterable<Item> items = itemService.getAll();

        if( items != null ) {
            return ResponseEntity.ok(items);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<?> createItem(
            @RequestBody ItemDto item,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Item created = itemService.create(item);

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
        Result<Item> result = itemService.replace(id, newItem);

        if( result.status() == Status.REPLACED ){
            return ResponseEntity.created(
                    uriComponentsBuilder.path("{id}").build(Map.of("id", result.data().getId()))
            ).body(result.data());
        } else if( result.status() == Status.CREATED ){
            return ResponseEntity.ok(result.data());
        }

        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> get(@PathVariable("id") UUID id) {
        return ResponseEntity.of(itemService.get(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") UUID id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
