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
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ItemDto extends AbstractDto {

    @NotNull(message = "{errors.title.not_null}")
    @Size(min = 3, max = 50, message = "{errors.title.size}")
    private String title;

    @Size(max = 1000, message = "{errors.description.size}")
    private String description;

    private List<CategoryDto> categories;

    public ItemDto(String title, String description) {
        super();

        setTitle(title);
        setDescription(description);
    }

    public ItemDto(UUID id, String title, String description, List<CategoryDto> catgories) {
        super();

        setId(id);
        setTitle(title);
        setDescription(description);
        setCategories(catgories);
    }
}
