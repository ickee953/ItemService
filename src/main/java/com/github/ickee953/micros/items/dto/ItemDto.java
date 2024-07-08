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

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ItemDto extends AbstractDto {

    @NotNull(message = "{errors.title.not_null}")
    @Size(min = 3, max = 50, message = "{errors.title.size}")
    private String title;

    @Size(max = 1000, message = "{errors.description.size}")
    private String description;

    private List<CategoryDto> categories;

}
