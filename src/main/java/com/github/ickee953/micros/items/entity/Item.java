/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.entity;

import com.github.ickee953.micros.core.entity.AbstractEntity;
import com.github.ickee953.micros.items.dto.ItemDto;
import com.github.ickee953.micros.items.dto.ItemUploadDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Collection;

@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "item")
public class Item extends AbstractEntity {

        @Column(name = "title")
        private String title;

        @Column(name = "description")
        private String description;

        @Column(name ="picture_path")
        private String titlePic;

        @ManyToMany(fetch = FetchType.LAZY)
        private Collection<Category> category;

        @Override
        public ItemDto forResponse() {
                return new ItemDto(
                        getId(),
                        getTitle(),
                        getDescription(),
                        category.stream().map(Category::forResponse).toList()
                );
        }
}
