package com.fullcycle.catalogo.video.application.category;

import com.fullcycle.catalogo.video.application.usecase.category.find.all.FindAllCategoryUseCase;
import com.fullcycle.catalogo.video.domain.entity.Category;
import com.fullcycle.catalogo.video.domain.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FindAllCategoryUseCaseTests {

    @InjectMocks
    private FindAllCategoryUseCase useCase;

    @Mock
    ICategoryRepository repository;

    @BeforeEach
    public void setUp() {
        useCase = new FindAllCategoryUseCase(repository);
    }

    @Test
    @DisplayName("it should be able to return all the categories")
    public void executeReturnAllCategories() {
        List<Category> categories = List.of(
                new Category("name 1", "description 1"),
                new Category("name 2", "description 2"),
                new Category("name 3", "description 3"));

        when(repository.findAll()).thenReturn(categories);

        var resultFound = repository.findAll();
        var useCaseResult = useCase.execute();

        assertThat(resultFound).isNotNull();
        assertThat(resultFound).hasSize(3);
        verify(repository, atMost(2)).findAll();

        assertThat(useCaseResult).isNotNull();
        assertThat(useCaseResult).hasSize(3);
    }

    @Test
    @DisplayName("it should be able to return an empty list when there is no category")
    public void executeReturnAnEmptyList() {
        List<Category> categories = List.of();

        when(repository.findAll()).thenReturn(categories);

        var useCaseResult = useCase.execute();

        assertThat(useCaseResult).isNotNull();
        assertThat(useCaseResult).hasSize(0);

        verify(repository, times(1)).findAll();
    }
}
