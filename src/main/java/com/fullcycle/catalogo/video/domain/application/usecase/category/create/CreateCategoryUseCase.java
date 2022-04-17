package com.fullcycle.catalogo.video.domain.application.usecase.category.create;

import com.fullcycle.catalogo.video.domain.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.catalogo.video.domain.entity.Category;
import com.fullcycle.catalogo.video.domain.repository.ICategoryRepository;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CreateCategoryUseCase implements ICreateCategoryUseCase {

    private ICategoryRepository repository;

    @Override
    public CategoryOutputData execute(CreateCategoryInputData inputData) {
        var created = repository.create(toDomain(inputData));

        return CategoryOutputData.fromDomain(created);
    }

    private Category toDomain(CreateCategoryInputData inputData) {
        return new Category(
                inputData.getName(),
                inputData.getDescription(),
                inputData.getIsActive());
    }

}
