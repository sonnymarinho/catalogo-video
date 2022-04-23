package com.fullcycle.catalogo.video.api.category;

import com.fullcycle.catalogo.video.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.catalogo.video.application.usecase.category.common.UpdateCategoryInputData;
import com.fullcycle.catalogo.video.application.usecase.category.create.CreateCategoryInputData;
import com.fullcycle.catalogo.video.application.usecase.category.create.ICreateCategoryUseCase;
import com.fullcycle.catalogo.video.application.usecase.category.find.all.IFindAllCategoriesUseCase;
import com.fullcycle.catalogo.video.application.usecase.category.find.by.id.IFindCategoryByIdUseCase;
import com.fullcycle.catalogo.video.application.usecase.category.remove.IRemoveCategoryUseCase;
import com.fullcycle.catalogo.video.application.usecase.category.update.IUpdateCategoryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CategoryEndpoint implements ICategoryEndpoint{

    private ICreateCategoryUseCase createCategoryUseCase;
    private IFindCategoryByIdUseCase findCategoryByIdUseCase;
    private IFindAllCategoriesUseCase findAllCategoriesUseCase;
    private IUpdateCategoryUseCase updateCategoryUseCase;
    private IRemoveCategoryUseCase removeCategoryUseCase;
    
    
    @Override
    public CategoryOutputData create(CreateCategoryInputData inputData) {
        return createCategoryUseCase.execute(inputData);
    }

    @Override
    public List<CategoryOutputData> findAll() {
        return findAllCategoriesUseCase.execute();
    }

    @Override
    public CategoryOutputData findById(UUID id) {
        return findCategoryByIdUseCase.execute(id);
    }

    @Override
    public void update(UUID id, UpdateCategoryInputData inputData) {
        updateCategoryUseCase.execute(id, inputData);
    }

    @Override
    public void delete(UUID id) {
        removeCategoryUseCase.execute(id);
    }
}
