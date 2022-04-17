package com.fullcycle.catalogo.video.application.usecase.category.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryInputData {
    private String name;
    private String description;
    private boolean isActive;
}
