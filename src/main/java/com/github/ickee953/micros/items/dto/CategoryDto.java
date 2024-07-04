package com.github.ickee953.micros.items.dto;

import com.github.ickee953.micros.core.entity.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDto extends AbstractDto {

    private String title;

    private CategoryDto parentCategory;

    private LocalDateTime createdAt;
}
