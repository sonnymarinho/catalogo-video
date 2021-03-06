package com.fullcycle.catalogo.video.application.usecase.category.create;

import com.fullcycle.catalogo.video.application.usecase.category.common.CategoryOutputData;

public interface ICreateCategoryUseCase {
    CategoryOutputData execute(CreateCategoryInputData inputData);
}
