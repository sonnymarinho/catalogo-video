package com.fullcycle.catalogo.video.domain.application.usecase.category.find.by.id;

import com.fullcycle.catalogo.video.domain.application.usecase.category.common.CategoryOutputData;

import java.util.UUID;

public interface IFindCategoryByIdUseCase {
    
    public CategoryOutputData execute(UUID id);
}
