/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.repository;

import com.github.ickee953.micros.items.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query(
        value = "SELECT c.id, c.title, c.parent_category_id, c.created_at " +
                "FROM category c, item_category ic " +
                "WHERE ic.category_id = c.id AND ic.item_id = :itemId"
        , nativeQuery = true
    )
    List<Category> findAllByItemId(@Param("itemId") UUID id);

}
