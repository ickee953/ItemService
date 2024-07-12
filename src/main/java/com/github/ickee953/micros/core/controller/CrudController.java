/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.core.controller;

import com.github.ickee953.micros.core.entity.AbstractDto;
import com.github.ickee953.micros.core.entity.AbstractEntity;
import com.github.ickee953.micros.core.common.Result;
import com.github.ickee953.micros.core.service.EntityService;
import com.github.ickee953.micros.core.common.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

@RequiredArgsConstructor
public abstract class CrudController<T extends AbstractEntity<D>, D extends AbstractDto> {

    private final EntityService<T, D> entityService;

    @Autowired
    protected MessageSource messageSource;

    @GetMapping
    public ResponseEntity<Iterable<D>> all() {
        Iterable<T> items = entityService.getAll();

        if( items != null ) {

            List<D> responseList = new LinkedList<>();
            items.forEach(entity -> responseList.add(entity.forResponse()));

            return ResponseEntity.ok(responseList);
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody D dto,
            BindingResult bindingResult, Locale locale
    ) {
        if( bindingResult.hasErrors() ) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                            messageSource.getMessage("errors.validation", new Object[0],
                                    "errors.validation", locale));

            problemDetail.setProperty("errors",
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .toList());

            return ResponseEntity.badRequest()
                    .body(problemDetail);
        } else {
            T created = entityService.create(dto);

            return ResponseEntity
                    .created(
                            ServletUriComponentsBuilder.fromCurrentRequest()
                                    .pathSegment("{itemId}")
                                    .buildAndExpand(Map.of("itemId", created.getId()))
                                    .toUri()
                    )
                    .body(created.forResponse());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> replace(
            @PathVariable("id") UUID id,
            @Valid @RequestBody D dto,
            BindingResult bindingResult, Locale locale
    ) {
        if( bindingResult.hasErrors() ) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                    messageSource.getMessage("errors.validation", new Object[0],
                            "errors.validation", locale));

            problemDetail.setProperty("errors",
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .toList());

            return ResponseEntity.badRequest()
                    .body(problemDetail);
        } else {

            Result<T> result = entityService.replace(id, dto);

            if (result.status() == Status.REPLACED) {
                return ResponseEntity.noContent().build();
            } else if (result.status() == Status.CREATED) {

                T created = result.data();

                return ResponseEntity
                        .created(
                                ServletUriComponentsBuilder.fromCurrentRequest()
                                        .pathSegment("{id}")
                                        .buildAndExpand(Map.of("id", created.getId()))
                                        .toUri()
                        )
                        .body(created.forResponse());
            }

            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<D> get(@PathVariable("id") UUID id) {

        Optional<T> entity = entityService.get(id);

        return entity.map(
                e -> ResponseEntity.ok(e.forResponse())
            ).orElseGet(() ->
                ResponseEntity.noContent().build()
            );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") UUID id) {
        entityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
