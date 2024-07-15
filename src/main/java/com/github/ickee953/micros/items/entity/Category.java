/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.entity;

import com.github.ickee953.micros.core.entity.AbstractEntity;
import com.github.ickee953.micros.items.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collection;

@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category extends AbstractEntity {

        @Column(name = "title", unique = true)
        private String title;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "parent_category_id")
        private Category parentCategory;

        @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
        private Collection<Category> childCategory;

        @Override
        public CategoryDto forResponse() {
                return new CategoryDto(
                        getId(),
                        getTitle(),
                        parentCategory == null ? null : parentCategory.forResponse()
                );
        }
}
