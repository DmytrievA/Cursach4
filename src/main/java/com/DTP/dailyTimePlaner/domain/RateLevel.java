package com.DTP.dailyTimePlaner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "rate_level")
public class RateLevel
        extends com.DTP.dailyTimePlaner.domain.Entity {

    @Column(name="name",columnDefinition = "VARCHAR(15)")
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}