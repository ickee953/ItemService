/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.service;

import com.github.ickee953.micros.core.entity.service.EntityService;
import com.github.ickee953.micros.core.entity.service.RelationEntityService;
import com.github.ickee953.micros.core.entity.utils.Result;
import com.github.ickee953.micros.items.dto.CategoryDto;
import com.github.ickee953.micros.items.dto.ItemDto;
import com.github.ickee953.micros.items.entity.Category;
import com.github.ickee953.micros.items.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService implements RelationEntityService<Category, ItemDto>, EntityService<Category, CategoryDto> {

    private final CategoryRepository categoryRepository;

    public List<Category> getForObject(ItemDto item) {
        return categoryRepository.findAllById(
                item.getCategory().stream().map(CategoryDto::getId
                ).toList());
    }


    @Override
    public Iterable<Category> getAll() {
        return null;
    }

    @Override
    public Category create(CategoryDto object) {
        return null;
    }

    @Override
    public Optional<Category> get(UUID id) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Result<Category> replace(UUID id, CategoryDto object) {
        return null;
    }
}
