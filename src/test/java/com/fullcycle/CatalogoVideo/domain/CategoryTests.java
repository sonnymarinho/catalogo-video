package com.fullcycle.catalogo-video.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
