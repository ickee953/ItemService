/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.dto;

import com.github.ickee953.micros.core.entity.AbstractDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDto extends AbstractDto {

    @NotNull(message = "{errors.title.not_null}")
    @Size(min = 3, max = 50, message = "{errors.title.size}")
    private String title;

    private CategoryDto parentCategory;

    public CategoryDto(UUID id, String title, CategoryDto parentCategory) {

        super();

        setId(id);
        setTitle(title);
        setParentCategory(parentCategory);

    }
}
