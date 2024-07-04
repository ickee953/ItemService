/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.entity;

import com.github.ickee953.micros.core.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends AbstractEntity {

        private String title;

        @ManyToOne(fetch = FetchType.LAZY)
        private Category parentCategory;

        private LocalDateTime createdAt;
}
