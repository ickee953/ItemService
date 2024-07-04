/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.service;

import com.github.ickee953.micros.core.entity.service.RelationEntityService;
import com.github.ickee953.micros.items.dto.CategoryDto;
import com.github.ickee953.micros.items.dto.ItemDto;
import com.github.ickee953.micros.items.entity.Category;
import com.github.ickee953.micros.items.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements RelationEntityService<Category, ItemDto> {

    private final CategoryRepository categoryRepository;

    public List<Category> getForObject(ItemDto item) {
        return categoryRepository.findAllById(
                item.getCategory().stream().map(CategoryDto::getId
                ).toList());
    }
}
