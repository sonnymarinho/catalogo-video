package com.fullcycle.catalogo.video.application.usecase.category.find.by.id;

import com.fullcycle.catalogo.video.application.usecase.category.common.CategoryOutputData;

import java.util.UUID;

public interface IFindCategoryByIdUseCase {
    
    public CategoryOutputData execute(UUID id);
}
