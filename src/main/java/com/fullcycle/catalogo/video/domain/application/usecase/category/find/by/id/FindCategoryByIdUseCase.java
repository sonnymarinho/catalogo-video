package com.fullcycle.catalogo.video.domain.application.usecase.category.find.by.id;

import com.fullcycle.catalogo.video.domain.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.catalogo.video.domain.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class FindCategoryByIdUseCase implements IFindCategoryByIdUseCase {
    
    private ICategoryRepository repository;
    
    public CategoryOutputData execute(UUID id) {
        return repository.findById(id)
            .map(CategoryOutputData::fromDomain)
            .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    };
}
