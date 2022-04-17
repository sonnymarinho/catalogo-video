package com.fullcycle.catalogo.video.domain.application.usecase.category.common;

import java.util.UUID;

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
}
