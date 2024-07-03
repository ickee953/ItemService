package com.github.ickee953.micros.items.service;

import com.github.ickee953.micros.items.dto.ItemDto;
import com.github.ickee953.micros.items.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getFor(ItemDto item);

}
