package com.github.ickee953.micros.core.entity.service;

import com.github.ickee953.micros.core.entity.AbstractDto;
import com.github.ickee953.micros.core.entity.AbstractEntity;

import java.util.List;

public interface RelationEntityService<T extends AbstractEntity, V extends AbstractDto> {

    List<T> getForObject(V object);

}
