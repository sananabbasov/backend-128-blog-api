package az.edu.itbrains.blog.dtos.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCreateDto {

    private String title;
    private String description;
    private boolean active;
    private Long categoryId;
}
