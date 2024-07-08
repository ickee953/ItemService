/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.service;

import com.github.ickee953.micros.core.entity.common.Status;
import com.github.ickee953.micros.core.entity.service.EntityService;
import com.github.ickee953.micros.core.entity.service.RelationEntityService;
import com.github.ickee953.micros.core.entity.common.Result;
import com.github.ickee953.micros.items.dto.CategoryDto;
import com.github.ickee953.micros.items.dto.ItemDto;
import com.github.ickee953.micros.items.entity.Category;
import com.github.ickee953.micros.items.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static com.github.ickee953.micros.core.entity.common.Status.CREATED;
import static com.github.ickee953.micros.core.entity.common.Status.REPLACED;

@Service
@RequiredArgsConstructor
public class CategoryService implements RelationEntityService<Category, ItemDto>, EntityService<Category, CategoryDto> {

    private final CategoryRepository categoryRepository;

    public Collection<Category> getForObject(ItemDto item) {

        return categoryRepository.findAllById(item.getCategories().stream().map(CategoryDto::getId).toList());

    }

    @Override
    public Collection<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(CategoryDto category) {

        Category newCategory = new Category()
                .setTitle(category.getTitle()
        );

        if( category.getParentCategory() != null ) {
            newCategory.setParentCategory(
                    categoryRepository.findById(category.getParentCategory().getId()).orElse(null)
            );
        }

        return categoryRepository.save(newCategory);
    }

    @Override
    public Optional<Category> get(UUID id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Result<Category> replace(UUID id, CategoryDto categoryDto) {

        AtomicReference<Status> status = new AtomicReference<>();

        Category newCategory = categoryRepository.findById(id)
                .map( item -> {

                    status.set(REPLACED);

                    item.setTitle(categoryDto.getTitle());

                    return categoryRepository.save(item);
                })
                .orElseGet( () -> {

                    status.set(CREATED);

                    return categoryRepository.save(
                            new Category()
                                    .setTitle(categoryDto.getTitle())
                    );
                });

        if( categoryDto.getParentCategory() != null ) {
            newCategory.setParentCategory(
                    categoryRepository.findById(categoryDto.getParentCategory().getId()).orElse(null)
            );
        }

        return new Result<>(status.get(), newCategory);

    }
}
