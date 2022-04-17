package com.fullcycle.catalogo.video.domain;

import java.util.UUID;

public class Category {
    private UUID id;
    private String name;
    private String description;
    private Boolean isActive = true;

    public Category(String name, String description) {
        this(UUID.randomUUID(), name, description);
    }

    public Category(UUID id, String name, String description) {
        setId(id);
        setName(name);
        this.description = description;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {

        if (name == null)
            throw new IllegalArgumentException("name cannot be null");
        if (name.isEmpty())
            throw new IllegalArgumentException("name cannot be empty");
        if (name.isBlank())
            throw new IllegalArgumentException("name cannot be blank");

        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean activate() {
        return this.isActive = true;
    }

    public Boolean deactivate() {
        return this.isActive = false;
    }
}
