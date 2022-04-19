package com.fullcycle.catalogo.video.application.usecase.category.common;

import java.util.UUID;

import com.fullcycle.catalogo.video.domain.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryOutputData {
    private UUID id;
    private String name;
    private String description;
    private boolean isActive;

    public static CategoryOutputData fromDomain(Category created) {
        return new CategoryOutputData(
                created.getId(),
                created.getName(),
                created.getDescription(),
                created.getIsActive());
    }
}
