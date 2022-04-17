package com.fullcycle.catalogo.video.application.category;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import static org.assertj.core.api.Assertions.assertThat;

import com.fullcycle.catalogo.video.domain.entity.Category;
import com.fullcycle.catalogo.video.domain.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.catalogo.video.domain.application.usecase.category.create.CreateCategoryInputData;
import com.fullcycle.catalogo.video.domain.application.usecase.category.create.CreateCategoryUseCase;
import com.fullcycle.catalogo.video.domain.repository.ICategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CreateCategoryUseCaseTests {

    @InjectMocks
    private CreateCategoryUseCase useCase;

    @Mock
    ICategoryRepository repository;

    @BeforeEach
    public void setUp() {
        useCase = new CreateCategoryUseCase(repository);
    }

    @Test
    @DisplayName("it should be able to create a new category")
    public void executeCreateCategory() {
        Category category = new Category(
                "Action",
                "Action category description");

        when(repository.create(any(Category.class))).thenReturn(category);

        CreateCategoryInputData input = new CreateCategoryInputData(
                category.getName(),
                category.getDescription(),
                category.getIsActive());

        CategoryOutputData output = useCase.execute(input);
        repository.create(category);

        assertThat(output.getName()).isEqualTo(category.getName());

    }

}
