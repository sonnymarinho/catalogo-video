package com.fullcycle.catalogo.video.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryTests {

    @Test
    @DisplayName("it should be able to create a new category through constructor")
    public void createCategoryWithName() {
        final Category category = new Category("name", "description");

        assertNotNull(category);

        assertNotNull(category.getId());

        assertEquals(category.getName(), "name");
        assertEquals(category.getDescription(), "description");
    }

    @Test
    @DisplayName("it should be able to create a new category through constructor using description as null")
    public void createCategoryWithNameAndNullDescription() {
        final Category category = new Category("name", null);

        assertNotNull(category);

        assertNotNull(category.getId());

        assertEquals(category.getName(), "name");
        assertEquals(category.getDescription(), null);
    }

    @Test
    @DisplayName("it should return a category that is activated by default")
    public void createAnActivatedCategory() {
        final Category category = new Category("name", "description");

        assertNotNull(category);

        assertNotNull(category.getId());
        assertEquals(category.getName(), "name");
        assertEquals(category.getDescription(), "description");

        assertTrue(category.getIsActive());
    }

    @Test
    @DisplayName("it should be able to deactivate a category")
    public void createAnDeactivatedCategory() {
        final Category category = new Category("name", "description");

        category.deactivate();

        assertNotNull(category);

        assertNotNull(category.getId());
        assertEquals(category.getName(), "name");
        assertEquals(category.getDescription(), "description");

        assertFalse(category.getIsActive());
    }

    @Test
    @DisplayName("it should not be able to create a category with a null name")
    public void createAndThrow() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Category(null, "description");
        });
    }

    @Test
    @DisplayName("it should not be able to create and update a category")
    public void createAndUpdate() {
        Category category = new Category("name 1", "description 1");

        assertNotNull(category);

        category.update("name 2", "description 2");

        assertEquals(category.getName(), "name 2");
        assertEquals(category.getDescription(), "description 2");
        assertTrue(category.getIsActive());
    }

    @Test
    @DisplayName("it should not be able to create and update a category also passing the active state")
    public void createAndUpdatePassingActiveAtribute() {
        Category category = new Category("name 1", "description 1");

        assertNotNull(category);

        category.update("name 2", "description 2", false);

        assertEquals(category.getName(), "name 2");
        assertEquals(category.getDescription(), "description 2");
        assertFalse(category.getIsActive());
    }

}
