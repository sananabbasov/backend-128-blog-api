package az.edu.itbrains.blog.dtos.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDto {

    private String title;
    private String description;
    private boolean active;
    private Long categoryId;
}
