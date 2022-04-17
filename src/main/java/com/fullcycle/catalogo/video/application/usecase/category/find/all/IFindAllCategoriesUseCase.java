package com.fullcycle.catalogo.video.application.usecase.category.find.all;

import com.fullcycle.catalogo.video.application.usecase.category.common.CategoryOutputData;

import java.util.List;

public interface IFindAllCategoriesUseCase { 
        public List<CategoryOutputData> execute();
}
