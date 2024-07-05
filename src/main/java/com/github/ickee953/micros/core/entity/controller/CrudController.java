/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.core.entity.controller;

import com.github.ickee953.micros.core.entity.AbstractDto;
import com.github.ickee953.micros.core.entity.AbstractEntity;
import com.github.ickee953.micros.core.entity.service.EntityService;
import com.github.ickee953.micros.core.entity.common.Result;
import com.github.ickee953.micros.core.entity.common.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class CrudController<T extends AbstractEntity, D extends AbstractDto> {

    private final EntityService<T, D> entityService;

    @GetMapping
    public ResponseEntity<Iterable<T>> all() {
        Iterable<T> items = entityService.getAll();

        if( items != null ) {
            return ResponseEntity.ok(items);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody D dto
    ) {
        T created = entityService.create(dto);

        return ResponseEntity
                .created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                        .pathSegment("{itemId}")
                        .buildAndExpand(Map.of("itemId", created.getId()))
                        .toUri()
                )
                .body(created);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> replace(
            @RequestBody D dto,
            @PathVariable("id") UUID id
    ) {
        Result<T> result = entityService.replace(id, dto);

        if( result.status() == Status.REPLACED ){
            return ResponseEntity.noContent().build();
        } else if( result.status() == Status.CREATED ){

            return ResponseEntity
                    .created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .pathSegment("{id}")
                                .buildAndExpand(Map.of("id", result.data().getId()))
                                .toUri()
            )
            .body(result.data());
        }

        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<T> get(@PathVariable("id") UUID id) {
        return ResponseEntity.of(entityService.get(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") UUID id) {
        entityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
