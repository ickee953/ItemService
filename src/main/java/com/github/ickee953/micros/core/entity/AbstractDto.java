package com.github.ickee953.micros.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public abstract class AbstractDto implements Serializable {

    private UUID id;

}
