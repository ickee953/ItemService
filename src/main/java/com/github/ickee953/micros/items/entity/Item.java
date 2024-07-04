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
import java.util.Collection;

@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Item extends AbstractEntity {

        private String title;

        private String description;

        @ManyToMany(fetch = FetchType.LAZY)
        private Collection<Category> category;

        private LocalDateTime createdAt;
}
