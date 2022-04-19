package com.fullcycle.catalogo.video.infrastructure.persistence;

import com.fullcycle.catalogo.video.domain.entity.Category;
import com.fullcycle.catalogo.video.domain.exception.NotNullException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Data @NoArgsConstructor @AllArgsConstructor
public class CategoryPersistence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "VARBINARY(16)")
    private UUID id;
    
    @Column
    @NotNull(message = "The name of the category cannot be null")
    @NotBlank(message = "The name of the category cannot be blank")
    private String name;
    
    @Column
    private String description;
    
    @Column
    private Boolean isActive;
    
    public static CategoryPersistence from(Category category) {
        if(category == null) throw new NotNullException("Category cannot be null");
        
        return new CategoryPersistence(
            category.getId(),
            category.getName(),
            category.getDescription(),
            category.getIsActive()
        );
    }
    
    public Category fromThis() {
        return new Category(getId(), getName(), getDescription(), getIsActive());
    }
}
