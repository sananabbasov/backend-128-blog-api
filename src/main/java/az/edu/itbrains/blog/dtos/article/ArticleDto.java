package az.edu.itbrains.blog.dtos.article;

import az.edu.itbrains.blog.dtos.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private Long id;
    private String title;
    private String description;
    private boolean active;
    private CategoryDto category;
}
