/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.controller;

import com.github.ickee953.micros.core.entity.controller.CrudController;
import com.github.ickee953.micros.items.dto.CategoryDto;
import com.github.ickee953.micros.items.entity.Category;
import com.github.ickee953.micros.items.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController extends CrudController<Category, CategoryDto> {

    public CategoryController(CategoryService service) {
        super(service);
    }

}
