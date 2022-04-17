package com.fullcycle.catalogo.video.domain.application.usecase.category.find.all;

import com.fullcycle.catalogo.video.domain.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.catalogo.video.domain.repository.ICategoryRepository;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FindAllCategoryUseCase implements IFindAllCategoriesUseCase {

    private ICategoryRepository repository;

    @Override
    public List<CategoryOutputData> execute() {
        var domainList = repository.findAll();
        
        return domainList.stream().map(CategoryOutputData::fromDomain).collect(Collectors.toList());
    }
}
