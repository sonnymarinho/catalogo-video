package com.fullcycle.catalogo.video.application.usecase.category.update;

import com.fullcycle.catalogo.video.application.usecase.category.common.UpdateCategoryInputData;

import java.util.UUID;

public interface IUpdateCategoryUseCase {
    void execute(UUID id, UpdateCategoryInputData inputData);
}
