package com.github.ickee953.micros.items.service;

import com.github.ickee953.micros.items.dto.CategoryDto;
import com.github.ickee953.micros.items.dto.ItemDto;
import com.github.ickee953.micros.items.entity.Category;
import com.github.ickee953.micros.items.repository.CategotyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategotyRepository categotyRepository;

    public List<Category> getFor(ItemDto item) {
        return categotyRepository.findAllById(
                item.category().stream().map(CategoryDto::id
                ).toList());
    }
}
