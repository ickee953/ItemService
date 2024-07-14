package com.github.ickee953.micros.items.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ItemUploadDto extends ItemDto {

    private MultipartFile picture;

}
