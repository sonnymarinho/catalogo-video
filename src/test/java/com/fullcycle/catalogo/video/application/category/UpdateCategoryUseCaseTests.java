package com.fullcycle.catalogo.video.application.category;

import com.fullcycle.catalogo.video.domain.application.exception.NotFoundException;
import com.fullcycle.catalogo.video.domain.application.usecase.category.common.UpdateCategoryInputData;
import com.fullcycle.catalogo.video.domain.application.usecase.category.update.UpdateCategoryUseCase;
import com.fullcycle.catalogo.video.domain.entity.Category;
import com.fullcycle.catalogo.video.domain.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UpdateCategoryUseCaseTests {

    @InjectMocks
    private UpdateCategoryUseCase useCase;

    @Mock
    ICategoryRepository repository;

    @BeforeEach
    public void setUp() {
        useCase = new UpdateCategoryUseCase(repository);
    }

    @Test
    @DisplayName("it should be able to update a category")
    public void shouldBeAbleToUpdateACategory() {
        Category existentCategory = new Category("existent name", "existent description", false);

        var updateInputData = new UpdateCategoryInputData();
        updateInputData.setName("new name");
        updateInputData.setDescription("new description");

        doNothing().when(repository).update(existentCategory);
        when(repository.findById(existentCategory.getId())).thenReturn(Optional.of(existentCategory));

        assertFalse(existentCategory.isActive());
        assertThat(existentCategory.getName()).isEqualTo("existent name");
        assertThat(existentCategory.getDescription()).isEqualTo("existent description");

        assertDoesNotThrow(() -> useCase.execute(existentCategory.getId(), updateInputData));

        assertThat(existentCategory.getName()).isEqualTo(updateInputData.getName());
        assertThat(existentCategory.getDescription()).isEqualTo(updateInputData.getDescription());
        assertThat(existentCategory.isActive()).isEqualTo(updateInputData.isActive());

        verify(repository, times(1)).update(existentCategory);
        verify(repository, times(1)).findById(existentCategory.getId());
    }

    @Test
    @DisplayName("it should not be able to update a category if it does not exist")
    public void shouldTrowIfNotExists() {
        Category existentCategory = new Category("existent name", "existent description", false);

        var updateInputData = new UpdateCategoryInputData();
        updateInputData.setName("new name");
        updateInputData.setDescription("new description");

        doNothing().when(repository).update(existentCategory);
        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> useCase.execute(existentCategory.getId(), updateInputData));

        verify(repository, times(0)).update(any(Category.class));
        verify(repository, times(1)).findById(existentCategory.getId());
    }
}
