package com.github.ickee953.micros.items.dto;

import com.github.ickee953.micros.core.entity.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ItemDto extends AbstractDto {

    private String title;

    private List<CategoryDto> category;

    private LocalDateTime createdAt;

}
