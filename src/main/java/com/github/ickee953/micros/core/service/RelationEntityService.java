/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.core.service;

import com.github.ickee953.micros.core.entity.AbstractEntity;
import com.github.ickee953.micros.core.entity.AbstractDto;

public interface RelationEntityService<T extends AbstractEntity, V extends AbstractDto> {

    Iterable<T> getForObject(V object);

}