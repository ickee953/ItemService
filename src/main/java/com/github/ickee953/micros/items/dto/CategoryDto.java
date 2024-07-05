/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.dto;

import com.github.ickee953.micros.core.entity.AbstractDto;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDto extends AbstractDto {

    @Size(min = 3, max = 50, message = "")
    private String title;

    private CategoryDto parentCategory;

    private LocalDateTime createdAt;

}
