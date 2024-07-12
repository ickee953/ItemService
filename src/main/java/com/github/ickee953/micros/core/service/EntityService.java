/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.core.service;

import com.github.ickee953.micros.core.entity.AbstractDto;
import com.github.ickee953.micros.core.entity.AbstractEntity;
import com.github.ickee953.micros.core.common.Result;

import java.util.Optional;
import java.util.UUID;

public interface EntityService<T extends AbstractEntity, V extends AbstractDto> {

    Iterable<T> getAll();

    T create(V object);

    Optional<T> get(UUID id);

    void delete(UUID id);

    Result<T> replace(UUID id, V object);

}
