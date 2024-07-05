/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.entity;

import com.github.ickee953.micros.core.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractEntity {

        private String title;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "parent_category_id")
        private Category parentCategory;

        //@OneToMany(mappedBy = "parentCategory")
        //private List<Category> childCategory;

        private LocalDateTime createdAt;
}
