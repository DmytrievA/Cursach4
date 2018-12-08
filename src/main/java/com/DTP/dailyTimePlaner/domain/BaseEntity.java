package com.DTP.dailyTimePlaner.domain;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.security.spec.InvalidParameterSpecException;

@Entity(name="baseEntity")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long entityId;

    public BaseEntity() {
    }

    public BaseEntity(Long entityId) {
        if(entityId<0)
            throw new InvalidParameterException();
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        if(entityId<0)
            throw new InvalidParameterException();
        this.entityId = entityId;
    }
}
